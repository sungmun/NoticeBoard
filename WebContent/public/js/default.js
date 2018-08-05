$('#navbar').mouseout(()=>$('#navbar').css('top',($('body').scrollTop() > 20 ||$('html').scrollTop()>20)?"-45px":"0"));
//$('#navbar').mouseover((bar)=>bar.style.top = "0");
$('#navbar').mouseover((bar)=>bar.currentTarget.style.top = "0");
$(window).scroll(()=>$('#navbar').css('top',($('body').scrollTop() > 20 ||$('html').scrollTop()>20)?"-45px":"0"));

$('#signUp').click(()=>location.href = '/NoticeBoard/JavaServerPage/SingUp/SingUpPage.jsp');
$('#signIn').click(()=>location.href = '/NoticeBoard/JavaServerPage/SingIn/SingInPage.jsp');



function showErrorModal(title,text){
	$('.modal-title>font').text(title)
	$('.modal-body>font').text(text);
	$('#showModal').modal();
	return false;
}