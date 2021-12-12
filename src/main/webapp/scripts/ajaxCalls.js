/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: 8 Dec, 2021
*
********************************************************************************/
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

/**
* call to add claim for the registered device selected
 */
function addClaim() {
	var deviceId = $('#deviceId').val();
	var date = $('#claimDate').val();
	var desc = $('#description').val();
	var successMsg = $('#successMsg');
	var errorMsg = $('#errorMsg');
	$.post('add-claim', {deviceId: deviceId, date: date, desc: desc}).done(function(data) {
		if(data.indexOf("claimError") > -1) {
			$('#claimError').addClass('show').removeClass('hide');
		} else {
			$('#claimError').addClass('hide').removeClass('show');
		}
        if(data == "Done") {
            successMsg.addClass('show').removeClass('hide');
            setTimeout(function(){
                successMsg.addClass('hide').removeClass('show');
            }, 5000);
        } else if(data == "Error"){
        	errorMsg.addClass('show').removeClass('hide');
   	        setTimeout(function(){
   	        	errorMsg.addClass('hide').removeClass('show');
   	        }, 5000);
        }
    })
}