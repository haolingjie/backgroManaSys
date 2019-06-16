$(function () {
    $("#jqGrid").Grid({
        url: '../breservationcard/uploadlist',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '卡号', name: 'cardcode', index: 'cardCode', width: 80},
			{label: '密码', name: 'password', index: 'passWord', width: 80},
			{label: '公司代码', name: 'comcode', index: 'comCode', width: 80},
			{label: '用户名称', name: 'username', index: 'userName', width: 80},
			{label: '性别', name: 'sex', index: 'sex', width: 80},
			{label: '身份证号', name: 'identitycard', index: 'identityCard', width: 80},
			{label: '手机号', name: 'phobenumber', index: 'phobeNumber', width: 80},
			{label: '体检机构', name: 'medicalcode', index: 'medicalCode', width: 80},
			{label: '体检日期', name: 'medicaldate', index: 'medicalDate', width: 80},
			{label: '医疗卡状态 0：未激活，1：已激活，2已预购，3已到检，4：已过期', name: 'cardstatus', index: 'cardStatus', width: 80},
			{label: '寄送地址', name: 'sendaddress', index: 'sendAddress', width: 80},
			{label: '信息编辑标识', name: 'modifyFlag', index: 'modifyFlag', width: 80},
			{label: '插入时间', name: 'inserttime', index: 'insertTime', width: 80, formatter: function (value) {
					return transDate(value);}},
			{label: '更新时间', name: 'operatetime', index: 'operateTime', width: 80, formatter: function (value) {
					return transDate(value);}},],
		rowList: [9999999],
		rowNum: 9999999,
    });
	new AjaxUpload('#upload', {
		action: '../breservationcard/upload',
		name: 'file',
		autoSubmit: true,
		responseType: "json",
		onSubmit: function (file, extension) {
			if (!(extension && /^(xls|xlsx)$/.test(extension.toLowerCase()))) {
				alert('只支持jpg、png、gif格式的图片！');
				return false;
			}
		},
		onComplete: function (file, r) {
			if (r.code == 0) {
				vm.reload();
			} else {
				alert(r.msg);
			}
		}
	});
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		bReservationcard: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: '',
			modifyFlag:'',
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.bReservationcard = {};
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
            let url = '../breservationcard/saveCardInf';
			var obj=$("#jqGrid").jqGrid("getRowData");

			Ajax.request({
			    url: url,
                params: JSON.stringify(vm.bReservationcard),
                type: "POST",
			    contentType: "application/json",
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
			});
		},
		saveCardInfo: function (event) {
			if(vm.q.modifyFlag == null || $.trim(vm.q.modifyFlag) == ""){
				alert('请选择寄送地址是否可让用户编辑标识');
				return;
			}
			var obj=$("#jqGrid").jqGrid("getRowData");
			var data={cardInfo : obj,modifyFlag : vm.q.modifyFlag};
			let url = "../breservationcard/saveCardInfo";
			Ajax.request({
				url: url,
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
		del: function (event) {
            let ids = getSelectedRows("#jqGrid");
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
				    url: "../breservationcard/delete",
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
                url: "../breservationcard/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.bReservationcard = r.bReservationcard;
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