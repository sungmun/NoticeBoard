<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel" aria-hidden="true" id="showModal">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">

			<div class="modal-header">
				<h4 class="modal-title">
					<font style="vertical-align: inherit;">경고</font>
				</h4>
			</div>
			<div class="modal-body">
				<font style="vertical-align: inherit;"><span></span><br>
					다시 확인해 주세요 </font>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function showErrorModal(text){
	$('.modal-body>font>span').remove();
	$('.modal-body>font').append($('<span/>',{text:text}));
	$('#showModal').modal();
	return false;
}
</script>