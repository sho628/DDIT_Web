/**
 * 
 */
let deleteForm = $("#deleteForm");
let boPassTag = deleteForm.find("[name='boPass']");
$("#delBtn").on("click", function(){
	let password = prompt("비밀번호");
	if(password){
		boPassTag.val(password);
		deleteForm.submit();
	}
});

$("#repBtn,#recBtn").on("click", function(){
	let clickBtn = $(this);
	let url = clickBtn.data("url");
	$.ajax({
		url : $.getContextPath() + url,
		data : {
			what:${board.boNo}
		},
		dataType : "text",
		success : function(resp) {
			if(resp=="OK"){
				let span = clickBtn.siblings("span:first");
				$(span).text( parseInt($(span).text())+1 );
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
});