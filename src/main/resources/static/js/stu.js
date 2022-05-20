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
            window.location.href="/select/pagestu/"+page+"/"+role;
        }else{
            window.location.href="/select/pagestu/"+(page-1)+"/"+role;
        }
    });
    $('#n-page').on("click",function () {
        if (page>=pages){
            window.location.href="/select/pagestu/"+page+"/"+role;
        }else{
            window.location.href="/select/pagestu/"+(page+1)+"/"+role;
        }
    });
    $('#f-page').on("click",function () {
        window.location.href="/select/pagestu/"+1+"/"+role;
    });
    $('#e-page').on("click",function () {
        window.location.href="/select/pagestu/"+pages+"/"+role;
    });
    $(".close-icon").on("click",function () {
        $("#add-box").hide();
        $("#edit-box").hide();
        $("#top").show();
        $("#box").show();
        $("#foot").show();
    });
    $("#stu-add").on("click",function () {
        $("#top").hide();
        $("#box").hide();
        $("#foot").hide();
        $("#edit-box").hide();
        $("#add-box").show();
    });
    $("#add-submit").on("click",function () {
        if($("#add_stu_id").val().length<=10){
            $.get("/select/stuById/"+$("#add_stu_id").val(),function (data) {
                if(data=="exist"){
                    alert("该学号已存在");
                }else {
                    $.get("/select/classById/"+$("#add_stu_classid").val(),function (data) {
                        if (data=="notexist"){
                            alert("该班级编号不存在");
                        }else{
                            $("#add-form").submit();
                        }
                    });
                }
            });
        }else{
            alert("学号不能太长噢");
        }

    });
    $(".stu-edit").on("click",function () {
        $.get("/select/stuEdit/"+$(this).parent().siblings(".i").children().val(),function (data) {
            if(data!="error"){
                var newData = JSON.parse(data);
                $("#edit_stu_id").val(newData.student_id);
                $("#edit_stu_name").val(newData.student_name);
                $("#edit_stu_sex").val(newData.student_sex);
                $("#edit_stu_birth").val(newData.student_birth);
                $("#edit_stu_phone").val(newData.student_phone);
                $("#edit_stu_email").val(newData.student_email);
                $("#edit_stu_password").val(newData.student_password);
                $("#edit_stu_role").val(newData.student_role);
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
        var id=$("#search_stu_id").val();
        var name=$("#search_stu_name").val();
        if(id!=""&&name!=""){
            window.location.href="/select/searchStuIdName/"+id+"/"+name+"/"+role;
        }else if(id==""&&name!=""){
            var value=name;
            window.location.href="/select/searchStuIdName/name."+value+"/"+role;
        }else if(id!=""&&name==""){
            var value=id;
            window.location.href="/select/searchStuIdName/id."+value+"/"+role;
        }
    });
    $("#stu-export").on("click",function () {
       window.location.href="/excel/stuExport";
    });
    $("#stu-import").on("click",function () {
       $("#stu-excel").click();
    });
    $("#stu-excel").on("change",function () {
        $("#form").submit();
    });
    var id="";
    $('#stu-del').on("click",function () {
        $(".td").each(function () {
            if($(this).is(":checked")){
                id+=$(this).val()+"s";
            }
        });
        if(confirm("你确定要删除？！")){
            if(prompt("请输入操作权限(管理员密码)")=="admin"){
                window.location.href="/delete/stu/"+id+"/"+role;
            }else{
                alert("权限错误无法删除");
            }
        }
    });
});