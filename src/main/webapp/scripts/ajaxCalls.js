/**
 * call to update the status of a claim when changed by the admin
 */
function claimStatusChanged(id) {
   	    var statusSelectBoxValue = $('#claimStatus' + id).val();
   	    var updatedMsg = $('#updatedMsg' + id);
   	    var errorMsg = $('#errorMsg' + id);
   	    $.post('update-claim-status', {claimId: id, status: statusSelectBoxValue}).done(function(data) {
   	        if(data == "Done") {
   	        	if(statusSelectBoxValue == "Approved"){
   	        		$('#claimStatus' + id).attr("disabled", true);
   	        	}
   	            updatedMsg.addClass('show').removeClass('hide');
   	            setTimeout(function(){
   	                updatedMsg.addClass('hide').removeClass('show');
   	            }, 5000);
   	        } else {
   	        	errorMsg.addClass('show').removeClass('hide');
 	   	        setTimeout(function(){
 	   	        	errorMsg.addClass('hide').removeClass('show');
 	   	        }, 5000);
   	        }
   	    })
   	}

/**
* call to fetch all users as per the value in the search box
 */

function searchUser() {
	var searchTerm = "%" + $('input[name=searchUser]').val() + "%";
	var userTable = $('#allUsersData');
	$.post('search-user', {search: searchTerm}).done(function(data) {
		userTable && userTable.html(data);
	})
}