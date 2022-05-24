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
            window.location.href="/select/pageclass/"+page+"/"+role;
        }else{
            window.location.href="/select/pageclass/"+(page-1)+"/"+role;
        }
    });
    $('#n-page').on("click",function () {
        if (page>=pages){
            window.location.href="/select/pageclass/"+page+"/"+role;
        }else{
            window.location.href="/select/pageclass/"+(page+1)+"/"+role;
        }
    });
    $('#f-page').on("click",function () {
        window.location.href="/select/pageclass/"+1+"/"+role;
    });
    $('#e-page').on("click",function () {
        window.location.href="/select/pageclass/"+pages+"/"+role;
    });
    $(".close-icon").on("click",function () {
        $("#add-box").hide();
        $("#edit-box").hide();
        $("#top").show();
        $("#box").show();
        $("#foot").show();
    });
    $("#class-add").on("click",function () {
        $("#top").hide();
        $("#box").hide();
        $("#foot").hide();
        $("#edit-box").hide();
        $("#add-box").show();
    });
    $("#add-submit").on("click",function () {
        $.get("/select/classById/"+$("#add_class_id").val(),function (data) {
            if(data=="exist"){
                alert("该班级编号已存在");
            }else {
                $("#add-form").submit();
            }
        });
    });
    $(".class-edit").on("click",function () {
        $.get("/select/classEdit/"+$(this).parent().siblings(".i").children().val(),function (data) {
            if(data!="error"){
                var newData = JSON.parse(data);
                $("#edit_class_id").val(newData.class_id);
                $("#edit_class_name").val(newData.class_name);
                $("#edit_student_num").val(newData.student_num);
                $("#edit_teacher_num").val(newData.teacher_num);
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
        var id=$("#search_class_id").val();
        var name=$("#search_class_name").val();
        if(id!=""&&name!=""){
            window.location.href="/select/searchClassIdName/"+id+"/"+name+"/"+role;
        }else if(id==""&&name!=""){
            var value=name;
            window.location.href="/select/searchClassIdName/name."+value+"/"+role;
        }else if(id!=""&&name==""){
            var value=id;
            window.location.href="/select/searchClassIdName/id."+value+"/"+role;
        }
    });
    $("#class-export").on("click",function () {
       window.location.href="/excel/classExport";
    });
    $("#class-import").on("click",function () {
       $("#class-excel").click();
    });
    $("#class-excel").on("change",function () {
        $("#form").submit();
    });
    var id="";
    $('#class-del').on("click",function () {
        $(".td").each(function () {
            if($(this).is(":checked")){
                id+=$(this).val()+"s";
            }
        });
        if(id!=""){

            if(confirm("你确定要删除？！")){
                if(prompt("请输入操作权限(管理员密码)")=="admin"){
                    window.location.href="/delete/class/"+id+"/"+role;
                }else{
                    alert("权限错误无法删除")
                }
            }
        }else{
            alert("请勾选要删出的内容")
        }
    });
});
