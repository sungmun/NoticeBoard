	var postNum=$('#postNum').text();
	commentLoad();

	function commentWrite(list){
		$('#comment>div').remove();
		$.each(list,function(index,comment){
			var total=$('<div/>');
			
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