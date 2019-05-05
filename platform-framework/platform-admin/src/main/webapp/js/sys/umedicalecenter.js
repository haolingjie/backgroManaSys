$(function () {
    $("#jqGrid").Grid({
        url: '../umedicalecenter/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '城市代码', name: 'citycode', index: 'cityCode', width: 80},
			{label: '城市名称', name: 'cityname', index: 'cityName', width: 80},
			{label: '地区代码', name: 'areacode', index: 'areaCode', width: 80},
			{label: '地区名称', name: 'areaname', index: 'areaName', width: 80},
			{label: '医疗品牌代码', name: 'medicalbrandcode', index: 'medicalBrandCode', width: 80},
			{label: '医疗品牌名称', name: 'medicalbrandname', index: 'medicalBrandName', width: 80},
			{label: '体检中心代码', name: 'medicalecentercode', index: 'medicalECenterCode', width: 80},
			{label: '体检中心名称', name: 'medicalecentername', index: 'medicalECenterName', width: 80},
			{label: '体检中心地址', name: 'medicalecenteraddress', index: 'medicalECenterAddress', width: 80},
			{label: '插入时间', name: 'inserttime', index: 'insertTime', width: 80},
			{label: '更新时间', name: 'operatetime', index: 'operateTime', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		uMedicalecenter: {},
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
			vm.uMedicalecenter = {};
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
            let url = vm.uMedicalecenter.id == null ? "../umedicalecenter/save" : "../umedicalecenter/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.uMedicalecenter),
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
				    url: "../umedicalecenter/delete",
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
                url: "../umedicalecenter/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.uMedicalecenter = r.uMedicalecenter;
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