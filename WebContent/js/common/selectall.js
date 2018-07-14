function toggle(source, cb) {
  checkboxes = document.getElementsByName(cb);
  for(var i in checkboxes)
    checkboxes[i].checked = source.checked;
}


function toggleByClass(source, cb) {
	  checkboxes = getElementsByClassName(cb);
	  for(var i in checkboxes)
	    checkboxes[i].checked = source.checked;
	}