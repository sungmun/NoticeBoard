
	var search='';
	var maxPage=$("#maxPage").text();
	
	$(document).on('click','#serchbtn',function(){
		search=$('#search').val();
		
		listLoad(1);
	});
	
	$(document).on('click','.page-link',function(){
		var page=$(this).text();
		maxPageLoad(page);
		listLoad(page);
		
	});
	
	$(document).on('click','tbody>tr',function(item){
		var num=$(this).find('td').first().text();
		location.href='./NoticeContent?id='+num;
		
	});
	
	function listChange(list){
		
		$('tbody>tr').remove();
		$.each(list,function(index,item){
		
			var tr=$('<tr/>',{class: 'notice_list'});
			
			tr.append($('<td/>',{text: item.notice_num}));
			tr.append($('<td/>',{text: item.notice_title}));
			tr.append($('<td/>',{text: item.member_id}));
			tr.append($('<td/>',{text: item.notice_date}));
			
			$("tbody").append(tr);
		});
		
	}
	
	function maxPageLoad(page) {
		var minNum=(page-4<=0)?1:page-4;
		
		var maxNum=(minNum+9>=maxPage)?maxPage:minNum+9;
		
		$('li.page-item').remove();
		
		for(var num=minNum;num<=maxNum;num++){
			
			var li=$('<li/>');
			li.addClass('page-item');
			if(num == page)	li.addClass('active');
			li.append($('<a/>',{
				class:'page-link',
				role:'button',
				text:num
				}));
			$('.pagination').append(li);
		}
		
	}
	
	function listLoad(number) {
		ajax("/NoticeBoard/ListLoad",{"page":number.trim(), "search":search},function(data){
			
			if(data.maxPage!=null)
				maxPage=data.maxPage;
			
			listChange(JSON.parse(data.list));

		});
		
	}
	
	