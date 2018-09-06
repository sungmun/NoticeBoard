
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
		var minNum=(page-4<=0)?1:page-4;// 최소값 지정(1이 최소이며, 현재 페이지에서 최대 4 작을수 있다)
		
		var maxNum=(minNum+9>=maxPage)?maxPage:minNum+9;
		// 최대값지정(최소값에서 9를 더한 값이며, 그 숫자는 maxPage값을 넘을수 없다)
		
		$('li.page-item').remove();// 버튼 초기화
		
		for(var num=minNum;num<=maxNum;num++){
			
			var li=$('<li/>',{class : 'page-item'});
			
			if(num == page)	li.addClass('active');// 현재 페이지에 클래스 추가
			
			var a=$('<a/>',{
				class:'page-link',
				role:'button',
				text:num
				});
			li.append(a);
			$('.pagination').append(li);
		}
		
	}
	
	function listLoad(number) {
		ajax("/NoticeBoard/ListLoad",{"page":number.trim(), "search":search},function(data){
			if(data.maxPage!=null)	maxPage=data.maxPage;
			
			listChange(JSON.parse(data.list));
		});
		
	}
	
	