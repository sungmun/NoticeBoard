	var postNum=$('#postNum').text();
	commentLoad();

	function commentWrite(list){
		console.log(list);
		var pre_comment=null;
		var comment_deep_count=0;
		var comment_deep=40;
		//	    margin-left: 40px;

		$('#comment>div').remove();
		$.each(list,function(index,comment){
			console.log(pre_comment);
			console.log(comment_deep_count);
			var total=$('<div/>');
			
			if(pre_comment == null){
			}else if(pre_comment.commentNum==comment.reCommentGroup){
				comment_deep_count++;
				total.css('margin-left',(comment_deep*comment_deep_count)+'px');
			}else if(pre_comment.reCommentGroup==comment.reCommentGroup){
				total.css('margin-left',(comment_deep*comment_deep_count)+'px');
			}else{
				comment_deep_count=0;
			}
			
			var post_meat=$('<div/>',{class:'post-meta'});
			
			var memberid=$('<div/>',{class:'pull-left'});
			
			memberid.append($('<a/>',{
				href:'#',
				text: comment.memberId
			}));
			post_meat.append(memberid);
			
			var commentday=$('<div/>',{
				class:'pull-right',
				text: comment.commentDay
			});
			post_meat.append(commentday);
			
			total.append(post_meat);
			total.append($('<br/>'));
			
			var commentContent=$('<section/>',{
				class: 'panel-body panel-default',
				text: comment.commentContents
			});
			
			total.append(commentContent);
			
			pre_comment=comment;
			$('#comment').append(total);
		});
		
		
	}
	
	$("#send").click(function() {
		ajax("/NoticeBoard/WriteComment",{post:'${notice.notice_num}',contents:$('#comment-area').val()},(json)=>showErrorModal(json.err));
		commentLoad('${notice.notice_num}');
	});
	
	function commentLoad() {
		ajax("/NoticeBoard/ReadComment",{"post":postNum},(json)=>commentWrite(JSON.parse(json.list)));
	}