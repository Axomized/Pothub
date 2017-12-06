$('body').on('change, keyup' , '.search_bar', function(){
		var thisvalue  = $(this).val().toLowerCase();
		var thislength = $(this).val().length;

		$('.single_chat').each(function(){
			var thischat = $(this);
			var name 	 = $(this).find('.user_name name').text().toLowerCase();

			if(name.indexOf(thisvalue) < 0){
				thischat.hide("slow");
			} else {
				thischat.show("slow");
			}
		});
	});