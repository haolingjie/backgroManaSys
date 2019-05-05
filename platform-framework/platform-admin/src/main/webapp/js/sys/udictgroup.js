$(function () {
    $("#jqGrid").Grid({
        url: '../udictgroup/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '组代码', name: 'groupcode', index: 'groupCode', width: 80},
			{label: '组名称', name: 'groupname', index: 'groupName', width: 80},
			{label: '类型代码', name: 'categorycode', index: 'categoryCode', width: 80},
			{label: '类型名称', name: 'categoryname', index: 'categoryName', width: 80},
			{label: '有效值', name: 'validstatus', index: 'validStatus', width: 80},
			{label: '插入时间', name: 'inserttime', index: 'insertTime', width: 80},
			{label: '更新时间', name: 'operatetime', index: 'operateTime', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		uDictGroup: {},
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
			vm.uDictGroup = {};
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
            let url = vm.uDictGroup.id == null ? "../udictgroup/save" : "../udictgroup/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.uDictGroup),
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
				    url: "../udictgroup/delete",
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
                url: "../udictgroup/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.uDictGroup = r.uDictGroup;
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
        }
	}
});