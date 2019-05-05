$(function () {
    $("#jqGrid").Grid({
        url: '../uuser/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '用户id', name: 'usercode', index: 'usercode', width: 80},
			{label: '用户名称', name: 'username', index: 'userName', width: 80},
			{label: '性别', name: 'sex', index: 'sex', width: 80},
			{label: '公司代码', name: 'comcode', index: 'comcode', width: 80},
			{label: '手机号', name: 'phobenumber', index: 'phobeNumber', width: 80},
			{label: '身份证号', name: 'identitycard', index: 'identityCard', width: 80},
			{label: '住宿地址', name: 'address', index: 'address', width: 80},
			{label: '插入时间', name: 'inserttime', index: 'insertTime', width: 80},
			{label: '更新时间', name: 'operatetime', index: 'operateTime', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		uUser: {},
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
			vm.uUser = {};
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
            let url = vm.uUser.id == null ? "../uuser/save" : "../uuser/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.uUser),
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
				    url: "../uuser/delete",
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
                url: "../uuser/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.uUser = r.uUser;
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