$(function () {

    var flag=[];
    $("#invite_code").blur(function () {
        if ($(this).val()=="666666"){
            flag[0]="s";
            $("#invite_code-img").attr("src","img/Tick.png");
        }else {
            flag[0]="邀请码错误 ";
            $("#invite_code-img").attr("src","img/Error.png");
        }
    });
    $("#admin_id").blur(function () {
        var value= $("#admin_id").val();
        if(/^(\d+){3,10}$/.test(value)){
            $.get("/select/exist/"+$("#admin_id").val(),function (data) {
                if(data=="exist"){
                    flag[1]="编号已存在 ";
                    $("#admin_id-img").attr("src","img/Error.png");
                }else {
                    flag[1]="s";
                    $("#admin_id-img").attr("src","img/Tick.png");
                }
            });
        }else{
            flag[1]="编号必须全部为数字，且大于3位，小于10位 ";
        }
    });
    $("#admin_password").blur(function () {
        if(/^\w{6,18}$/.test($(this).val())){
            flag[2]="s";
            $("#admin_password-img").attr("src","img/Tick.png");
            if($("#admin_password").val()==$("#re_admin_password").val()){
                $("#re_admin_password-img").attr("src","img/Tick.png");
            }
        }else {
            flag[2]="密码必须为字母数字下划线,且大于6位，小于18位 ";
            $("#admin_password-img").attr("src","img/Error.png");
        }
    });
    $("#re_admin_password").blur(function () {
        if (/^\w{6,18}$/.test($(this).val())&&$(this).val()==$("#admin_password").val()){
            flag[3]="s";
            $("#re_admin_password-img").attr("src","img/Tick.png");
        }else {
            flag[3]="两次密码不一致 ";
            $("#re_admin_password-img").attr("src","img/Error.png");
        }
    });
    $("#security_answer").blur(function () {
        var options=$("#security_question option:selected");
       if(options.val()!=""){
           if($(this).val().length>0){
               flag[4]="s";
               $("#security_answer-img").attr("src","img/Tick.png");
           }else {
               flag[4]="答案不能为空 ";
               $("#security_answer-img").attr("src","img/Error.png");
           }
       }else{
           flag[4]="安全问题不能为空 ";
           $("#security_answer-img").attr("src","img/null.png");
       }
    });
    $("#admin_email").blur(function () {
        if(/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/.test($(this).val())){
            flag[5]="s";
            $("#admin_email-img").attr("src","img/Tick.png");
        }else{
            flag[5]="邮箱不合法 ";
            $("#admin_email-img").attr("src","img/Error.png");
        }
    });
    $("#admin_sex").change(function () {
        $("#admin_sex-img").attr("src","img/Tick.png");
    });
    var index=0;
    $("#register").on("click",function () {
        for(var i=0;i<flag.length;i++){
            if(flag[i]!="s"){
                alert(flag[i]);
                if(i=4){
                    $("#security_answer").val("");
                }
                break;
            }else if(flag[i]=="s"){
                index=index+1;
            }
        }
        if(index==6){
            $("#form").submit();
            // var layer_iframe = parent.layer.getFrameIndex(window.name);
            // parent.layer.close(layer_iframe);
        }
        index=0;
    });
});