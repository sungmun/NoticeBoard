	var postNum=$('#post').data('noticenum');
	
	//--------------------Comment Part--------------------
	
	commentReadAjax();

	function commentWrite(list,callback){

		$('#comment>blockquote').remove();
		$.each(list,function(index,comment){
			var total=$('<blockquote/>');
			
			total.data('comment',comment);
			
			if(comment.commentNum!=comment.reCommentGroup){
				total.css('margin-left','40px');
			}
			
			var post_meat=$('<div/>',{class:'post-meta'});
			post_meat.append($('<div/>',{class:'pull-left',text: comment.memberId}));
			post_meat.append($('<div/>',{class:'pull-right',text: comment.commentDay}));
			total.append(post_meat);
			
			total.append($('<br/>'));
			
			var commentContent=$('<section/>',{class: 'panel-body panel-default', text: comment.commentContents});
			total.append(commentContent);
			
			if(comment.commentNum==comment.reCommentGroup){
				var addComment=$('<section/>',{class: 'panel-footer'});
				addComment.append($('<div/>',{class:'pull-right',text:'댓글달기'}));
				total.append(addComment);
			}
			
			$('#comment').append(total);
			
		});
		
	}
	
	var footer=null;
	$(document).on('click','blockquote>.panel-footer>.pull-right',function(){
		if(footer==null){
			footer=$(this).parent();
		}else{
			footer.text('');
			footer.css('padding-bottom','15px');
			footer.append($('<div/>',{
				class:'pull-right',
				text:'댓글달기'
			}));
			
			footer=$(this).parent();
		}
		footer.text('');
		footer.css('padding-bottom','5px');
		$('#comment_input>div>.input-group').clone().appendTo(footer);
		footer.find('#comment-area').val('');
	});
	
	$(document).on('click','blockquote #send',function(){
		var send=$(this).parent().parent().parent();
		
		var data={
			reComment:send.data('comment').commentNum,
			post:postNum,
			contents:send.find('#comment-area').val()
		}
		commentWriteAjax(data);
	});
	
	$(document).on('click','#comment_input #send',function() {
		var comments=$(this).parent().find('#comment-area');
		commentWriteAjax({post:postNum,contents:comments.val()});
	});
	
	function commentWriteAjax(data){
		ajax("/NoticeBoard/WriteComment",data,(json)=>showErrorModal('경고',json.err));
		commentReadAjax();	
	}
	
	function commentReadAjax() {
		ajax("/NoticeBoard/ReadComment",
				{"post":postNum},
				(json)=>commentWrite(JSON.parse(json.list)));
		//(DOM)=>$('#comment').append(DOM)
	}