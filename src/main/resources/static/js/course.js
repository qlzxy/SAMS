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
            window.location.href="/select/pagecourse/"+page+"/"+role;
        }else{
            window.location.href="/select/pagecourse/"+(page-1)+"/"+role;
        }
    });
    $('#n-page').on("click",function () {
        if (page>=pages){
            window.location.href="/select/pagecourse/"+page+"/"+role;
        }else{
            window.location.href="/select/pagecourse/"+(page+1)+"/"+role;
        }
    });
    $('#f-page').on("click",function () {
        window.location.href="/select/pagecourse/"+1+"/"+role;
    });
    $('#e-page').on("click",function () {
        window.location.href="/select/pagecourse/"+pages+"/"+role;
    });
    $(".close-icon").on("click",function () {
        $("#add-box").hide();
        $("#edit-box").hide();
        $("#top").show();
        $("#box").show();
        $("#foot").show();
    });
    $("#course-add").on("click",function () {
        $("#top").hide();
        $("#box").hide();
        $("#foot").hide();
        $("#edit-box").hide();
        $("#add-box").show();
    });
    $("#add-submit").on("click",function () {
        $.get("/select/courseById/"+$("#add_course_id").val(),function (data) {
            if(data=="exist"){
                alert("该课程编号已存在");
            }else {
                $("#add-form").submit();
            }
        });
    });
    $(".course-edit").on("click",function () {
        $.get("/select/courseEdit/"+$(this).parent().siblings(".i").children().val(),function (data) {
            if(data!="error"){
                var newData = JSON.parse(data);
                $("#edit_course_id").val(newData.course_id);
                $("#edit_course_name").val(newData.course_name);
                $("#edit_course_hour").val(newData.course_hour);
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
        var id=$("#search_course_id").val();
        var name=$("#search_course_name").val();
        if(id!=""&&name!=""){
            window.location.href="/select/searchCourseIdName/"+id+"/"+name+"/"+role;
        }else if(id==""&&name!=""){
            var value=name;
            window.location.href="/select/searchCourseIdName/name."+value+"/"+role;
        }else if(id!=""&&name==""){
            var value=id;
            window.location.href="/select/searchCourseIdName/id."+value+"/"+role;
        }
    });
    $("#course-export").on("click",function () {
       window.location.href="/excel/courseExport";
    });
    $("#course-import").on("click",function () {
       $("#course-excel").click();
    });
    $("#course-excel").on("change",function () {
        $("#form").submit();
    });
    var id="";
    $('#course-del').on("click",function () {
        $(".td").each(function () {
            if($(this).is(":checked")){
                id+=$(this).val()+"s";
            }
        });
        if(confirm("你确定要删除？！")){
            if(prompt("请输入操作权限(管理员密码)")=="admin"){
                window.location.href="/delete/course/"+id+"/"+role;
            }else{
                alert("权限错误无法删除")
            }
        }
    });
});