	var postNum=$('#post').data('noticenum');
	commentLoad();

	function commentWrite(list){
		var pre_comment=null;
		var comment_deep_count=0;
		var comment_deep=40;
		// margin-left: 40px;

		$('#comment>blockquote').remove();
		$.each(list,function(index,comment){
			
			var memberid=$('<div/>',{class:'pull-left'});
			memberid.append($('<a/>',{
				href:'#',
				text: comment.memberId
			}));
			
			var commentday=$('<div/>',{
				class:'pull-right',
				text: comment.commentDay
			});

			
			var post_meat=$('<div/>',{class:'post-meta'});
			post_meat.append(memberid);
			post_meat.append(commentday);
			
			var commentContent=$('<section/>',{
				class: 'panel-body panel-default',
				text: comment.commentContents
			});
			
			var addComment=$('<section/>',{
				class: 'panel-footer',
				css: {'font-size':'10px',
					'padding-top': '3px',
					'padding-bottom':'15px'
					}
			});
			addComment.append($('<a/>',{
				class:'pull-right',
				text:'댓글달기'
			}));
// addComment.append($('#comment_input>div>.input-group'))
			var total=$('<blockquote/>',{
				css: {'font-size':'13px'}
			});
			total.data('comment',comment);
			if(pre_comment != null&&pre_comment.commentNum==comment.reCommentGroup){
				comment_deep_count++;
			}else{
				comment_deep_count=0;
			}
			
			total.css('margin-left',(comment_deep*comment_deep_count)+'px');
			total.append(post_meat);
			total.append($('<br/>'));
			total.append(commentContent);
			total.append(addComment);
			pre_comment=comment;
			$('#comment').append(total);
		});
		
	}
	
	$(document).on('click','blockquote #send',function(){
		var send=$(this).parent();
		var data={
				commentContents:'',
				commentDay:'',
				commentNum:482,
				memberId:"tmuzzullo7r",
				noticeNum:0,
				reCommentGroup:482,
		}
	});
	
	var footer=null;
	$(document).on('click','blockquote>.panel-footer>a',function(){
		if(footer==null){
			footer=$(this).parent();
		}else{
			footer.text('');
			footer.css( {'font-size':'10px',
				'padding-top': '3px',
				'padding-bottom':'15px'
				});
			footer.append($('<a/>',{
				class:'pull-right',
				text:'댓글달기'
			}));
			footer=$(this).parent();
		}
		footer.css({
			'padding-top': '5px',
			'padding-bottom':'5px'
		})
		footer.text('');
		$('#comment_input>div>.input-group').clone().appendTo(footer);
	});
	
	$("#send").click(function() {
		ajax("/NoticeBoard/WriteComment",{post:postNum,contents:$('#comment-area').val()},(json)=>showErrorModal('경고',json.err));
		commentLoad('${notice.notice_num}');
	});
	
	function commentLoad() {
		ajax("/NoticeBoard/ReadComment",{"post":postNum},(json)=>commentWrite(JSON.parse(json.list)));
	}