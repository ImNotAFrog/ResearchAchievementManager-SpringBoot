
layui.use(['layer', 'table', 'element'], function () {
    layer = layui.layer //弹层
        , table = layui.table //表格
    var startDate = GetQueryString("startDate");
    var endDate = GetQueryString("endDate");
    var department = GetQueryString("department");
    var subDepartment = GetQueryString("subDepartment");
    var type = GetQueryString("type");
    var subject = GetQueryString("subject");
    var level = GetQueryString("level");
    var dataList = [];
    var departmentList = [];
    var mytable = null;
    var load=layer.load();
    function getInvolvementValue(value) {
        if (value == 1) {
            return "组长"
        } else {
            return "研究人员"
        }
    }

    function getProjectLevelValue(value) {
        if (value == 1) {
            return "国家级"
        } else if (value == 2) {
            return "省部级"
        } else if (value == 3) {
            return "市局级"
        } else if (value == 4) {
            return "校级"
        }
    }

    function getSubjectValue(value) {
        if (value == 1) {
            return "公安消防部队高等专科学校"
        } else if (value == 2) {
            return "外单位"
        }
    }
    function getProjectStatusValue(value) {
        if (value == 1) {
            return "通过验收"
        } else if (value == 2) {
            return "立项在研"
        }
    }

    function getThesisLevelValue(value) {
        if (value == 1) {
            return "《Science》、《Nature》及其子刊《Cell》"
        } else if (value == 2) {
            return "SCI收录"
        } else if (value == 3) {
            return "EI（JA）、SSCI、A&HCI收录"
        } else if (value == 4) {
            return "EI（CA）、CPCI"
        }
        else if (value == 5) {
            return "核心刊物正刊(含北大核心期刊、CSCD、CSSCI、RCCSE)"
        }
        else if (value == 6) {
            return "《武警学院学报》、《消防技术与产品信息》、全国性或行业性学会论文集（一等奖）、国际会议论文集"
        }
        else if (value == 7) {
            return "专业期刊（正刊）、全国性或行业性学会论文集（二等奖）、《消防科学与技术》增刊、《武警学院学报》增刊、《消防技术与产品信息》增刊"
        }
        else if (value == 8) {
            return "一般期刊、其它增刊、其它论文集、校刊"
        }
    }

    function getTextbookLevel(value) {
        if (value == 1) {
            return "公安消防部队高等专科学校正式出版"
        } else if (value == 2) {
            return "公安消防部队高等专科学校内部印刷教辅"
        } else if (value == 3) {
            return "外单位正式出版"
        }
    }

    function getTextbookInvolvementValue(value) {
        if (value == 1) {
            return "主编"
        } else if (value == 2) {
            return "副主编"
        } else if (value == 3) {
            return "参编"
        }
    }

    function getTextbookInvolvementValue(value) {
        if (value == 1) {
            return "主编"
        } else if (value == 2) {
            return "副主编"
        } else if (value == 3) {
            return "参编"
        }
    }

    function getPatentTypeValue(value) {
        if (value == 1) {
            return "发明专利"
        } else if (value == 2) {
            return "实用新型专利"
        } else if (value == 3) {
            return "外观设计专利"
        } else if (value == 4) {
            return "计算机软件著作权"
        }
    }

    function getLawsLevel(value) {
        if (value == 1) {
            return "法律、行政法规、国家标准"
        } else if (value == 2) {
            return "部门规章、行业标准规范"
        } else if (value == 3) {
            return "地方性法规、政府规章、地方性标准规范"
        }
    }

    function getLawsInvolvementValue(value) {
        if (value == 1) {
            return "主编"
        } else if (value == 2) {
            return "参编"
        }
    }

    function getReformProjectInvolvementValue(value) {
        if (value == 1) {
            return "组长"
        } else if (value == 2) {
            return "研究人员"
        }
    }

    function getReformProjectStatusValue(value) {
        if (value == 1) {
            return "通过验收"
        } else {
            return "立项在研"
        }
    }





    //获取字段信息与格式化数据
    function getField(type, dataList) {
        if (type == "project") {
            var t = "";
            var dep = null;
            //格式化课题类的数据
            $.each(dataList, function (index, items) {
                dataList[index].involvement = getInvolvementValue(items.involvement);
                dataList[index].level = getProjectLevelValue(items.level);
                dataList[index].subject = getSubjectValue(items.subject);
                dataList[index].status = getProjectStatusValue(items.status);
                if (typeof (items.project_date) != "undefined") {
                    t = items.project_date.time + "";
                    dataList[index].project_date = timestampToTime(t.substring(0, 10));
                } else {
                    dataList[index].project_date = "空";
                }
                dep = checkDep(departmentList, dataList[index].department, dataList[index].sub_department);
                dataList[index].department = dep.dep;
                dataList[index].sub_department = dep.subDeps;
            });
            return [
                { field: 'department', title: '所属部门', fixed: 'left' }
                , { field: 'sub_department', title: '所属科室' }
                , { field: 'p_name', title: '课题名称' }
                , { field: 'involvement', title: '作者参与情况' }
                , { field: 'level', title: '项目级别' }
                , { field: 'subject', title: '项目主体' }
                , { field: 'status', title: '项目状态', sort: true }
                , { field: 'project_date', title: '发表日期', sort: true }
            ]
        } else if (type == "thesis") {
            //格式化论文类的数据
            $.each(dataList, function (index, items) {
                dataList[index].journal_level = getThesisLevelValue(items.journal_level);
                if (typeof (items.publish_date) != "undefined") {
                    t = items.publish_date.time + "";
                    dataList[index].publish_date = timestampToTime(t.substring(0, 10));
                } else {
                    dataList[index].publish_date = "空";
                }
                dep = checkDep(departmentList, dataList[index].department, dataList[index].sub_department);
                dataList[index].department = dep.dep;
                dataList[index].sub_department = dep.subDeps;
            });
            return [
                { field: 'department', title: '所属部门', fixed: 'left' }
                , { field: 'sub_department', title: '所属科室' }
                , { field: 't_name', title: '论文名称' }
                , { field: 'journal_name', title: '发表刊物' }
                , { field: 'journal_level', title: '刊物级别' }
                , { field: 'journal_id', title: '刊号' }
                , { field: 'publish_date', title: '发表日期', sort: true }
            ]

        } else if (type == "textbook") {
            //格式化论著、教材类的数据
            $.each(dataList, function (index, items) {
                dataList[index].level = getTextbookLevel(items.level);
                dataList[index].involvement = getTextbookInvolvementValue(items.involvement);
                if (typeof (items.publish_date) != "undefined") {
                    t = items.publish_date.time + "";
                    dataList[index].publish_date = timestampToTime(t.substring(0, 10));
                } else {
                    dataList[index].publish_date = "空";
                }
                dep = checkDep(departmentList, dataList[index].department, dataList[index].sub_department);
                dataList[index].department = dep.dep;
                dataList[index].sub_department = dep.subDeps;
            });
            return [
                { field: 'department', title: '所属部门', fixed: 'left' }
                , { field: 'sub_department', title: '所属科室' }
                , { field: 'tb_name', title: '论著成果名称' }
                , { field: 'level', title: '论著级别' }
                , { field: 'involvement', title: '作者参与情况' }
                , { field: 'publisher', title: '出版社' }
                , { field: 'ISBN', title: 'ISBN' }
                , { field: 'publish_date', title: '发表日期', sort: true }
            ]

        } else if (type == "patent") {
            //格式化论著、教材类的数据
            $.each(dataList, function (index, items) {
                dataList[index].type = getPatentTypeValue(items.type);
                if (typeof (items.publish_date) != "undefined") {
                    t = items.publish_date.time + "";
                    dataList[index].publish_date = timestampToTime(t.substring(0, 10));
                } else {
                    dataList[index].publish_date = "空";
                }
                dep = checkDep(departmentList, dataList[index].department, dataList[index].sub_department);
                dataList[index].department = dep.dep;
                dataList[index].sub_department = dep.subDeps;
            });
            return [
                { field: 'department', title: '所属部门', fixed: 'left' }
                , { field: 'sub_department', title: '所属科室' }
                , { field: 'pa_name', title: '名称' }
                , { field: 'type', title: '类型' }
                , { field: 'author_rank', title: '作者排名' }
                , { field: 'pa_num', title: '专利编号' }
                , { field: 'pa_owner', title: '专利发明人' }
                , { field: 'publish_date', title: '授权公告日期', sort: true }
            ]
        } else if (type == "reform_project") {
            //格式化教改类的数据
            $.each(dataList, function (index, items) {
                dataList[index].involvement = getReformProjectInvolvementValue(items.involvement);
                dataList[index].status = getReformProjectStatusValue(items.status);
                if (typeof (items.project_date) != "undefined") {
                    t = items.project_date.time + "";
                    dataList[index].project_date = timestampToTime(t.substring(0, 10));
                } else {
                    dataList[index].project_date = "空";
                }
                dep = checkDep(departmentList, dataList[index].department, dataList[index].sub_department);
                dataList[index].department = dep.dep;
                dataList[index].sub_department = dep.subDeps;
            });
            return [
                { field: 'department', title: '所属部门', fixed: 'left' }
                , { field: 'sub_department', title: '所属科室' }
                , { field: 'r_p_name', title: '教改项目名称' }
                , { field: 'involvement', title: '作者参与情况' }
                , { field: 'status', title: '教改状态' }
                , { field: 'project_date', title: '立项/结题时间', sort: true }
            ]
        } else if (type == "laws") {
            //格式化法律类的数据
            $.each(dataList, function (index, items) {
                dataList[index].level = getLawsLevel(items.level);
                dataList[index].involvement = getLawsInvolvementValue(items.involvement);
                if (typeof (items.publish_date) != "undefined") {
                    t = items.publish_date.time + "";
                    dataList[index].publish_date = timestampToTime(t.substring(0, 10));
                } else {
                    dataList[index].publish_date = "空";
                }
                dep = checkDep(departmentList, dataList[index].department, dataList[index].sub_department);
                dataList[index].department = dep.dep;
                dataList[index].sub_department = dep.subDeps;
            });
            return [
                { field: 'department', title: '所属部门', fixed: 'left' }
                , { field: 'sub_department', title: '所属科室' }
                , { field: 'l_name', title: '法律、法规名称' }
                , { field: 'l_num', title: '法律、法规编号' }
                , { field: 'level', title: '级别' }
                , { field: 'involvement', title: '主编参与情况' }
                , { field: 'words_count', title: '字数' }
                , { field: 'publish_date', title: '发表日期', sort: true }
            ]
        } else {
            //全部内容的参数的数据
            $.each(dataList, function (index, items) {
                dataList[index].type = checkType(items.type);
                if (typeof (items.upload_date) != "undefined") {
                    t = items.upload_date.time + "";
                    dataList[index].upload_date = timestampToTime(t.substring(0, 10));
                } else {
                    dataList[index].upload_date = "空";
                }
                dep = checkDep(departmentList, dataList[index].department, dataList[index].sub_department);
                dataList[index].department = dep.dep;
                dataList[index].sub_department = dep.subDeps;
            });
            return [
                { field: 'department', title: '所属部门', fixed: 'left' }
                , { field: 'sub_department', title: '所属科室' }
                , { field: 'name', title: '成果名称' }
                , { field: 'type', title: '成果类型' }
                , { field: 'upload_date', title: '上传时间', sort: true }
            ]
        }
    }

    //取回部门信息并渲染表格；
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: { Authorization: 'Bearer ' + token },
        url: "/user/getDepartmentList",
        success: function (data) {
            var result = JSON.parse(data)
            if (result.state == "success") {
                departmentList = result.dep;
                renderTable();
            } else {
                layer.alert("获取数据异常", { icon: 5 }, function () {
                    closeIframe();
                })
            }

        }
    });


    function renderTable() {
        ajax_request({
            type: 'POST',
            url: '/achievement/getExportList',
            data: {
                type: type,
                startDate: startDate,
                endDate: endDate,
                department: department,
                subDepartment: subDepartment,
                subject: subject,
                level: level
            },
            success: function (res) {
                res = JSON.parse(res);
                if (res.state == 'success') {
                    dataList = res.data;
                    var FieldArr = getField(type, dataList);
                    mytable = table.render({
                        elem: '#dataTable'
                        , data: res.data
                        , page: true //开启分页
                        , cols: [FieldArr]
                    });
                    layer.close(load);
                } else {
                    layer.alert("获取数据异常", { icon: 5 }, function () {
                        closeIframe();
                    })
                }
            }
        })
    }














    // 导出成果
    $(".print").click(function (e) {
        if (dataList.length == 0) {
            return false;
        }
        table.exportFile(mytable.config.id, dataList, "xlsx");
        return false;
    });






})