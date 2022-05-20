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
            window.location.href="/select/pageteacher/"+page+"/"+role;
        }else{
            window.location.href="/select/pageteacher/"+(page-1)+"/"+role;
        }
    });
    $('#n-page').on("click",function () {
        if (page>=pages){
            window.location.href="/select/pageteacher/"+page+"/"+role;
        }else{
            window.location.href="/select/pageteacher/"+(page+1)+"/"+role;
        }
    });
    $('#f-page').on("click",function () {
        window.location.href="/select/pageteacher/"+1+"/"+role;
    });
    $('#e-page').on("click",function () {
        window.location.href="/select/pageteacher/"+pages+"/"+role;
    });
    $(".close-icon").on("click",function () {
        $("#add-box").hide();
        $("#edit-box").hide();
        $("#top").show();
        $("#box").show();
        $("#foot").show();
    });

    $("#teacher-add").on("click",function () {
        $("#top").hide();
        $("#box").hide();
        $("#foot").hide();
        $("#edit-box").hide();
        $("#add-box").show();
    });
    $("#add-submit").on("click",function () {
        if($("#add_teacher_id").val().length<=10){
        $.get("/select/teacherById/"+$("#add_teacher_id").val(),function (data) {
            if(data=="exist"){
                alert("该教师编号已存在");
            }else {
                $.get("/select/courseById/"+$("#add_teacher_courseid").val(),function (data) {
                    if (data=="notexist"){
                        alert("该课程编号不存在");
                    }else{
                        $("#add-form").submit();
                    }
                });
            }
        });
        }else{
            alert("教师编号不能太长噢");
        }
    });
    $(".teacher-edit").on("click",function () {
        $.get("/select/teacherEdit/"+$(this).parent().siblings(".i").children().val(),function (data) {
            if(data!="error"){
                var newData = JSON.parse(data);
                $("#edit_teacher_id").val(newData.teacher_id);
                $("#edit_teacher_name").val(newData.teacher_name);
                $("#edit_teacher_sex").val(newData.teacher_sex);
                $("#edit_teacher_birth").val(newData.teacher_birth);
                $("#edit_teacher_phone").val(newData.teacher_phone);
                $("#edit_teacher_email").val(newData.teacher_email);
                $("#edit_teacher_password").val(newData.teacher_password);
                $("#edit_teacher_role").val(newData.teacher_role);
                if(prompt("请输入操作权限(管理员密码)")=="admin"){
                    $("#top").hide();
                    $("#box").hide();
                    $("#foot").hide();
                    $("#add-box").hide();
                    $("#edit-box").show();
                }else{
                    alert("权限错误无法编辑");
                }
            }else{
                alert("目前正在维护中。。。");
            }
        });
    });
    $("#search-id-name").on("click",function () {
        var id=$("#search_teacher_id").val();
        var name=$("#search_teacher_name").val();
        if(id!=""&&name!=""){
            window.location.href="/select/searchTeacherIdName/"+id+"/"+name+"/"+role;
        }else if(id==""&&name!=""){
            var value=name;
            window.location.href="/select/searchTeacherIdName/name."+value+"/"+role;
        }else if(id!=""&&name==""){
            var value=id;
            window.location.href="/select/searchTeacherIdName/id."+value+"/"+role;
        }
    });
    $("#teacher-export").on("click",function () {
       window.location.href="/excel/teacherExport";
    });
    $("#teacher-import").on("click",function () {
       $("#teacher-excel").click();
    });
    $("#teacher-excel").on("change",function () {
        $("#form").submit();
    });
    var id="";
    $('#teacher-del').on("click",function () {
        $(".td").each(function () {
            if($(this).is(":checked")){
                id+=$(this).val()+"s";
            }
        });
        if(confirm("你确定要删除？！")){
            if(prompt("请输入操作权限(管理员密码)")=="admin"){
                window.location.href="/delete/teacher/"+id+"/"+role;
            }else{
                alert("权限错误无法删除")
            }
        }

    });
});