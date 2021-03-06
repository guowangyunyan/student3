

function replyToResponse(reviewId){
    $('#responseButton' + reviewId).fadeOut();
    $('#responseDiv' + reviewId).fadeIn();
    $('#saveOrCancelP' + reviewId).fadeIn();
}

function cancelSubmitResponse(reviewId){
    $('#responseButton' + reviewId).fadeIn();
    $('#responseDiv' + reviewId).fadeOut();
    $('#saveOrCancelP' + reviewId).fadeOut();
}
function editResponse(reviewId,text){
    $('#existResponseDiv' + reviewId).fadeOut();
    var value = $('#responseContent' + reviewId).html() ;

    value = value.replace(/<br>/g,"\n");
    $('#editContentTextArea' + reviewId).val( value);
    if ( $('#editContentTextArea' + reviewId).val().length  > 30 ){
        $('#editMax' + reviewId).html (text);
    }
    $('#editResponseCountP' + reviewId).html(  $('#responseContent' + reviewId).html().length);
    $('#editResponseDiv' + reviewId).fadeIn();

}
function cancelUpdateResponse(reviewId){
    $('#existResponseDiv' + reviewId).fadeIn();
    $('#editResponseDiv' + reviewId).fadeOut();
}
function updateResponse(reviewId,responseId){

    var newContent = $('#editContentTextArea' + reviewId).val();
    $('#responseContent' + reviewId).html(  newContent.replace(/\n/g,"<br>"));

    if ( newContent.length < 30) {
        toastr["error"](i18nMessage["review.response.content.tooshort"]);
        return ;
    }
    $('#updateBotton' + reviewId).attr("disabled",true);
    $.ajax({
        type:"POST",
        url: ctx +"/reviews/response/" + responseId,
        data:{"content": newContent},
        dataType:"json",
        success:function (data){
            if (data.code == 0 ) {
                $('#existResponseDiv' + reviewId).fadeIn();
                $('#editResponseDiv' + reviewId).fadeOut();
                $('#updateBotton' + reviewId).attr("disabled",false);
                toastr["success"] (i18nMessage["success"]);
                window.location.reload();
            }else{
                alert ( data.message);
                $('#updateBotton' + reviewId).attr("disabled",false);
            }
        }
    });
}

function cancelResponse(reviewId){

    $('#existResponseDiv' + reviewId).fadeIn();
    $('#editResponseDiv' + reviewId).fadeOut();

}

function deleteResponse(_this,responseId,reviewId,title,message,cancel,confirm){

    $(_this).confirm({
        'title' : title,  //'<@spring.message 'useraccounts.deleteuser' />',
        'message' : message, //'<@spring.message 'useraccounts.confirmdeleteuser' />',
        template: '<div  id="myModal" class="modal confirm-modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true"><div class="modal-dialog modal-lg"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button><h4 class="modal-title" id="myLargeModalLabel">Large modal</h4></div><div class="modal-body"><p class="modal-body-content" ></p></div><div class="modal-footer"><button type="button" class="btn btn-default" name="cancelBtn" data-dismiss="modal">' + cancel + '</button><button type="button" class="btn btn-danger confirm-btn" name="confirmBtn">' + confirm + '</button></div></div></div></div>',
        'action' : function(){
            $('#deleteButton'+reviewId).attr("disabled",true);
            $('#existResponseDiv' + reviewId).fadeOut();
            $('#responseButton' + reviewId).fadeIn();
            $.ajax({
                type:"delete",
                url: ctx +"/reviews/response/" + responseId,
                dataType:"json",
                success:function (data){
                    if (data.code == 0 ) {
                        $('#myModal').modal('hide');
                        toastr["success"] (i18nMessage["success"]);
                        window.location.reload();
                    }else{
                        alert ( data.message);
                        $('#deleteButton' + reviewId).attr("disabled",false);
                    }
                }
            });
    }
});

}

function submitResponse(reviewId,title,message,cancel,confirm){
    var content = $('#textarea' + reviewId).val();
    if ( content.length < 30) {
        toastr["error"](i18nMessage["review.response.content.tooshort"]);
        return ;
    }
    $('#submitBotton' + reviewId).attr("disabled","true");
    $.ajax({
        type:"post",
        url: ctx +"/reviews/"+reviewId+"/response",
        data:{"content":content},
        dataType:"json",
        success:function (data){
            if (data.code == 0 ) {
            	toastr["success"] (i18nMessage["success"]);
                window.location.reload();
                //当餐厅老板回复评论给用户时，发送邮件
                //sendEmailToUser(reviewId);
            }else{
                toastr["error"] ( data.message);
                $('#submitBotton' + reviewId).attr("disabled","false");
            }
        }
    });
}
//当餐厅老板回复评论给用户时，发送邮件
function sendEmailToUser(reviewId){
	var url=ctx +"/reviews/sendEmailToUser.json";
	$.post(url,{reviewId:reviewId}, function (data){
		location.reload();
	});
}

function changeCreateCount(reviewId,maxtext,mintext){
    if ( $('#textarea' + reviewId).val().length  > 30 ){
        $('#createMax' + reviewId).html (maxtext);
    }else{
        $('#createMax' + reviewId).html (mintext);
    }
   $('#createResponseCountP' + reviewId).html ($('#textarea' + reviewId).val().length );
}

function changeEditCount(reviewId,maxtext,mintext){
    if ( $('#editContentTextArea' + reviewId).val().length  > 30 ){
        $('#editMax' + reviewId).html (maxtext);
    }else{
        $('#editMax' + reviewId).html (mintext);
    }
    $('#editResponseCountP' + reviewId).html ($('#editContentTextArea' + reviewId).val().length );
}
