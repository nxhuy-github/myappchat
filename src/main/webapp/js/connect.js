$(document).ready(function(){
    $("#btnSubmit").click(function(event){
        event.preventDefault();
        let userName = $("#username").val();
        signUp(userName);
    });

    function signUp(userName){
        $.ajax({
            url: 'dispatcher/users/ListUser',
            type: 'POST',
            data: {userName: userName},
            dataType: 'text',
            success: function(data, textStatus, xhr){
                if(xhr.statusCode().status === 200){
                    sessionStorage.setItem("userName", userName);
                    $(location).attr('href', 'pageUtilisateur.html');
                }else{

                }
            },
            error: function(error){
                console.error(error);
            }
        });
    }
});
