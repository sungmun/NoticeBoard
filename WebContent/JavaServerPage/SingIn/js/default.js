var idCheck=false;
		
$('#idOverlapCheck').click(function(){
	if($('#id').val()==''){
		showErrorModal('경고','아이디를 입력해 주세요.');
	}
	ajax("/NoticeBoard/Check",{id:$('#id').val()},function(data){
		if(data.return){
			showErrorModal('경고','이미 존재하는 아이디 입니다.');
			$('#id').focus();
			cheack=true;
		}else{
			showErrorModal('성공','존재하지 않는 아이디 입니다.');
			$('#password').focus();
			cheack=false;
		}
	});
});
$('#form').bind('submit',()=> (form.password2 == form.password1)?isIdDuplicateCheck():showModal('비밀번호가 같지 않습니다.'));


function isIdDuplicateCheck(){
	return (!idCheck)?true:showErrorModal('경고','아이디 중복체크를 해주세요');
}
