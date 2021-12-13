/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: 6 Dec, 2021
*
********************************************************************************/
$(document).ready(function() {
	var today = new Date();
	var month = today.getMonth() + 1;
    var day = today.getDate();
    var year = today.getFullYear();

    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();

    var maxDate = year + '-' + month + '-' + day;    
	$('#purchaseDate').attr('max', maxDate); // disable future dates
})
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