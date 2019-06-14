$(function () {
    $("#jqGrid").Grid({
        url: '../mcardmessage/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '卡号', name: 'cardcode', index: 'cardCode', width: 80},
			{label: '医疗卡状态 0：未激活，1：已激活，2已预购，3已到检，4：已过期', name: 'cardstatus', index: 'cardStatus', width: 80},
			{label: '消息有效位：0无效，1有效', name: 'status', index: 'status', width: 80},
			{label: '插入时间', name: 'inserttime', index: 'insertTime', width: 80, formatter: function (value) {
					return transDate(value);}},
			{label: '更新时间', name: 'operatetime', index: 'operateTime', width: 80, formatter: function (value) {
					return transDate(value);}}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		mCardmessage: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: '',
			cardStatus: '',
			delayMonth: '',
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		activite: function () {
			let ids = getAllSelectedRows("#jqGrid");
			if (ids == null){
				return;
			}
			Ajax.request({
				url: "../mcardmessage/activite",
				params: JSON.stringify(ids),
				type: "POST",
				contentType: "application/json",
				successCallback: function (r) {
					alert('操作成功', function (index) {
						vm.reload();
					});
				}
			});
		},
		delay: function () {
			let ids = getAllSelectedRows("#jqGrid");
			if (ids == null){
				return;
			}
			if(vm.delayMonth== null || vm.delayMonth == 0){
				alert('请选择延期时间');
			}
			var data={ids:ids,delayMonth:vm.q.delayMonth};
			Ajax.request({
				url: "../mcardmessage/delay",
				params: JSON.stringify(data),
				type: "POST",
				contentType: "application/json",
				successCallback: function (r) {
					alert('操作成功', function (index) {
						vm.reload();
					});
				}
			});
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.mCardmessage = {};
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
            let url = vm.mCardmessage.id == null ? "../mcardmessage/save" : "../mcardmessage/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.mCardmessage),
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
				    url: "../mcardmessage/delete",
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
                url: "../mcardmessage/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.mCardmessage = r.mCardmessage;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'cardcode': vm.q.name,'cardstatus':vm.q.cardStatus},
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