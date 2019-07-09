$(function () {
    $("#jqGrid").Grid({
        url: '../breservationcard/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '卡号', name: 'cardcode', index: 'cardCode', width: 80},
			{label: '密码', name: 'password', index: 'passWord', width: 80},
			{label: '公司代码', name: 'comcode', index: 'comCode', width: 80},
			{label: '用户名称', name: 'username', index: 'userName', width: 80},
			{label: '性别', name: 'sex', index: 'sex', width: 80,formatter: function (value) {
				if(value =="1"){
					return '男'
				}else if(value == '0'){
					return '女';
				}else{
					return value;
				}
			}},
			{label: '身份证号', name: 'identitycard', index: 'identityCard', width: 80},
			{label: '手机号', name: 'phobenumber', index: 'phobeNumber', width: 80},
			{label: '体检机构', name: 'medicalcode', index: 'medicalCode', width: 80, formatter: function (value) {
			return getMedicalNameById(value);
			}},
			{label: '体检日期', name: 'medicaldate', index: 'medicalDate', width: 80, formatter: function (value) {
					return transDate(value,"yyyy-MM-dd");}},
			{label: '医疗卡状态', name: 'cardstatus', index: 'cardStatus', width: 80,formatter: function (value) {
					if(value =="0"){
						return '未激活'
					}else if(value == '1'){
						return '已激活';
					}else if(value == '2'){
						return '已预购';
					}else if(value == '3'){
						return '已到检';
					}else if(value == '4'){
						return '已过期';
					}else{
						return value;
					}
			}},
			{label: '寄送地址', name: 'sendaddress', index: 'sendAddress', width: 80},
			{label: '信息编辑标识', name: 'modifyFlag', index: 'modifyFlag', width: 80, formatter: function (value) {
					if(value =="0"){
						return '均可编辑'
					}else if(value == '1'){
						return '用户信息可修改，寄送地址不可修改';
					}else if(value == '2'){
						return '用户信息不可修改，寄送地址可修改';
					}else if(value == '3'){
						return '均不可修改';
					}else{
						return value;
					}
			}},
			{label: '预约套餐类型', name: 'setMeal', index: 'setMeal', width: 80},
			{label: '有效日期起始日期', name: 'startDate', index: 'startDate', width: 80, formatter: function (value) {
					return transDate(value);}},
			{label: '有效日期结束日期', name: 'endDate', index: 'endDate', width: 80, formatter: function (value) {
					return transDate(value);}},
			{label: '插入时间', name: 'inserttime', index: 'insertTime', width: 80, formatter: function (value) {
					return transDate(value);}},
			{label: '更新时间', name: 'operatetime', index: 'operateTime', width: 80, formatter: function (value) {
					return transDate(value);}},],
    });
    /*Ajax.request({
        url: "../umedicalecenter/getAll",
        async: true,
        successCallback: function (r) {
            if(r.uMedicalecenterList) {
                allmedicalCenterList = r.uMedicalecenterList;
            }
        }
    });*/
});
// var allmedicalCenterList;
function getMedicalNameById(id){
    var medicalName="";
    if(id != null && id !=''){
        Ajax.request({
            url: "../umedicalecenter/info/"+id,
            async: false,
            successCallback: function (r) {
                if(r.uMedicalecenter) {
                    medicalName = r.uMedicalecenter.medicalecentername;
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
		bReservationcard: {},
        medicalCenterList: [],
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: '',
			cardstatus: '',

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
            let url = vm.bReservationcard.id == null ? "../breservationcard/save" : "../breservationcard/update";
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
                postData: {'name': vm.q.name,'cardstatus':vm.q.cardstatus},
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
        },remoteMethod: function (query) {
            if (query !== '') {
                this.loading = true;
                $.ajax({
                    url: "../umedicalecenter/getAll",
                    data:{
                        name:query
                    },
                    type: "POST",
                    dateType:'json',
                    success:function(r) {
                        if(r.uMedicalecenterList) {
                            vm.medicalCenterList = r.uMedicalecenterList;
                        }
                    }
                });
            } else {
                this.medicalCenterList = [];
            }
        },
		getMedicalCode: function (val) {
			vm.bReservationcard.medicalcode=val;
		},
	}
});