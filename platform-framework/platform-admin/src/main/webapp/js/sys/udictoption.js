$(function () {
    $("#jqGrid").Grid({
        url: '../udictoption/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '明细代码', name: 'optioncode', index: 'optionCode', width: 80},
			{label: '明细名称', name: 'optionname', index: 'optionName', width: 80},
			{label: '明细所属组', name: 'groupCodeId', index: 'groupCodeId', width: 80, formatter: function (value) {
					return getGroupNameById(value);
			}},
			{label: '明细主要说明', name: 'optionimport', index: 'optionImport', width: 80},
			{label: '明细描述', name: 'optiondescribe', index: 'optionDescribe', width: 80},
			{label: '有效值', name: 'validstatus', index: 'validStatus', width: 80},
			{label: '插入时间', name: 'inserttime', index: 'insertTime', width: 80, formatter: function (value) {
					return transDate(value);}},
			{label: '更新时间', name: 'operatetime', index: 'operateTime', width: 80, formatter: function (value) {
					return transDate(value);}},]
	});
});

function getGroupNameById(id){
	var medicalName="";
	if(id != null && id !=''){
		Ajax.request({
			url: "../udictgroup/info/"+id,
			async: false,
			successCallback: function (r) {
				if(r.uDictGroup) {
					medicalName = r.uDictGroup.categoryname;
				}
			}
		});
	}
	return medicalName;
}

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		uDictOption: {},
		udictgroupList:[],
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.uDictOption = {};
		},
		update: function (event) {
            let id = getSelectedRow("#jqGrid");
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
            let url = vm.uDictOption.id == null ? "../udictoption/save" : "../udictoption/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.uDictOption),
                type: "POST",
			    contentType: "application/json",
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
			});
		},
		del: function (event) {
            let ids = getSelectedRows("#jqGrid");
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
				    url: "../udictoption/delete",
                    params: JSON.stringify(ids),
                    type: "POST",
				    contentType: "application/json",
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
					}
				});
			});
		},
		getInfo: function(id){
            Ajax.request({
                url: "../udictoption/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.uDictOption = r.uDictOption;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                name: ''
            };
            vm.reload();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
		remoteMethod: function (query) {
			if (query !== '') {
				this.loading = true;
				$.ajax({
					url: "../udictgroup/queryAll",
					data:{
						name:query
					},
					type: "POST",
					dateType:'json',
					success:function(r) {
						if(r.list) {
							vm.udictgroupList = r.list;
						}
					}
				});
			} else {
				vm.udictgroupList = [];
			}
		},
	}
});