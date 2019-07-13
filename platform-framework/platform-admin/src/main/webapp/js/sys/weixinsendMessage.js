$(function () {
    $("#jqGrid").Grid({
        url: '../breservationcard/uploadWeiXinSendMessageList',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '卡号', name: 'cardcode', index: 'cardCode', width: 80},
			{label: '用户名称', name: 'username', index: 'userName', width: 80},
			{label: '性别', name: 'sex', index: 'sex', width: 80},
			{label: '身份证号', name: 'identitycard', index: 'identityCard', width: 80},
			{label: '手机号', name: 'phobenumber', index: 'phobeNumber', width: 80},
			{label: '体检机构', name: 'medicalcode', index: 'medicalCode', width: 80},
			{label: '体检日期', name: 'medicaldate', index: 'medicalDate', width: 80},
			{label: '医疗卡状态 0：未激活，1：已激活，2已预购，3已到检，4：已过期', name: 'cardstatus', index: 'cardStatus', width: 80},
			]
    });
	new AjaxUpload('#upload', {
		action: '../breservationcard/uploadWeiXinSendMessage',
		name: 'file',
		autoSubmit: true,
		responseType: "json",
		onSubmit: function (file, extension) {
			if (!(extension && /^(xls|xlsx)$/.test(extension.toLowerCase()))) {
				alert('只支持xls、xlsx、格式的文本！');
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
			messageFlag:'',
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
			// var obj=$("#jqGrid").jqGrid("getRowData");

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
			var allID = $("#jqGrid").jqGrid('getDataIDs'); //这里获取所有行 主键id 是全的
			obj.push($("#jqGrid").jqGrid('getRowData', allID[allID.length-1]));
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
		sendMessage: function (event) {
			if(vm.q.messageFlag == null || $.trim(vm.q.messageFlag) == ""){
				alert('请选择微信公众号消息推送标识');
				return;
			}
			var obj=$("#jqGrid").jqGrid("getRowData");
			var allID = $("#jqGrid").jqGrid('getDataIDs'); //这里获取所有行 主键id 是全的
			obj.push($("#jqGrid").jqGrid('getRowData', allID[allID.length-1]));
			var data={cardInfo : obj,messageFlag : vm.q.messageFlag};
			let url = "../breservationcard/sendWeiXinMessage";
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