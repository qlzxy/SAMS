$(function () {
    if(message=="保存出错"){
        alert("保存出错");
    }
    $('#th').on('click',function(){
        if ($('#th').is(':checked')) {
            $('.td').each(function() {
                $(this).prop("checked", true);
            });
        } else {
            $('.td').each(function() {
                $(this).prop("checked", false);
            });
        }
    });
    $('#u-page').on("click",function () {
        if (page<=1){
            window.location.href="/select/page/"+page+"/"+role;
        }else{
            window.location.href="/select/page/"+(page-1)+"/"+role;
        }
    });
    $('#n-page').on("click",function () {
        if (page>=pages){
            window.location.href="/select/page/"+page+"/"+role;
        }else{
            window.location.href="/select/page/"+(page+1)+"/"+role;
        }
    });
    $('#f-page').on("click",function () {
        window.location.href="/select/page/"+1+"/"+role;
    });
    $('#e-page').on("click",function () {
        window.location.href="/select/page/"+pages+"/"+role;
    });
    $(".close-icon").on("click",function () {
        $("#add-box").hide();
        $("#edit-box").hide();
        $("#top").show();
        $("#box").show();
        $("#foot").show();
    });
    $("#student-add").on("click",function () {
        $.get("/select/findNoScoreStudent",function (data) {
            if(data!="error"){
                var newData = JSON.parse(data);
                $("#add_student_id option").remove();
                $("#add_student_id").append("<option value=''>直接选择或搜索选择</option>");
                $.each(newData, function(i, p){
                    $("#add_student_id").append("<option class='option'>"+p.student_id+"</option>");
                });
            }
        });

        $("#top").hide();
        $("#box").hide();
        $("#foot").hide();
        $("#edit-box").hide();
        $("#add-box").show();
    });
    $("#add_student_id").on("change",function(){
        var options=$("#add_student_id option:selected");
        $.get("/select/findStudentNameByStudentId/"+options.val(),function (data) {
            if(data!="error"){
                var newData=JSON.parse(data);
                $("#add_student_name").val(newData.student_name);
            }
        });
    });
    $(".student-edit").on("click",function () {
        $.get("/select/studentEdit/"+$(this).parent().siblings(".i").children().val(),function (data) {
            if(data!="error"){
                var newData = JSON.parse(data);
                $("#edit_student_id").val(newData[0].student_id);
                $("#edit_student_name").val(newData[0].student_name);
                $("#edit_YuWen").val(newData[0].yu_wen);
                $("#edit_ShuXue").val(newData[0].shu_xue);
                $("#edit_YingYu").val(newData[0].ying_yu);
                $("#edit_WuLi").val(newData[0].wu_li);
                $("#edit_HuaXue").val(newData[0].hua_xue);
                $("#edit_ShengWu").val(newData[0].sheng_wu);
                $("#edit_ZhengZhi").val(newData[0].zheng_zhi);
                $("#edit_LiShi").val(newData[0].li_shi);
                $("#edit_DiLi").val(newData[0].di_li);
                if(prompt("请输入操作权限(管理员密码)")=="admin"){
                    $("#top").hide();
                    $("#box").hide();
                    $("#foot").hide();
                    $("#add-box").hide();
                    $("#edit-box").show();
                }else{
                    alert("权限错误无法编辑")
                }

            }else{
                alert("目前正在维护中。。。");
            }
        });
    });
    $("#search-id-name").on("click",function () {
        var id=$("#search_student_id").val();
        var name=$("#search_student_name").val();
        if(id!=""&&name!=""){
            window.location.href="/select/searchStudentIdName/"+id+"/"+name+"/"+role;
        }else if(id==""&&name!=""){
            var value=name;
            window.location.href="/select/searchStudentIdName/name."+value+"/"+role;
        }else if(id!=""&&name==""){
            var value=id;
            window.location.href="/select/searchStudentIdName/id."+value+"/"+role;
        }
    });
    $("#student-export").on("click",function () {
       window.location.href="/excel/scoreExport";
    });
    $("#student-import").on("click",function () {
       $("#student-excel").click();
    });
    $("#student-excel").on("change",function () {
        $("#form").submit();
    });
    var id="";
    $('#student-del').on("click",function () {
        $(".td").each(function () {
            if($(this).is(":checked")){
                id+=$(this).val()+"s";
            }
        });
        if(id!=""){
            if(confirm("你确定要删除？！")){
                if(prompt("请输入操作权限(管理员密码)")=="admin"){
                    window.location.href="/delete/studentScore/"+id+"/"+role;
                }else{
                    alert("权限错误无法删除")
                }
            }
        }else{
            alert("请勾选要删出的内容")
        }


    });
});
