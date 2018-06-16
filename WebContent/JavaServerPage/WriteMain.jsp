<form action=""${pageContext.request.contextPath}/Write" method="post" class="for">
	<div class="form-group">
		<label for="title">Title</label> <input type="text" name="title"
			id="title" class="form-control">
	</div>
	<div class="form-group">
		<label for="content">Content</label>
		<textarea name="content" id="editor" class="form-control">
            </textarea>
	</div>
	<div class="pull-right">
		<button type="submit" class="btn btn-default">Sucess</button>
	</div>

</form>

<script src="../ckeditor4/ckeditor.js"></script>
<script>
	CKEDITOR.replace('editor');
	CKEDITOR.config.height = 600;
</script>
