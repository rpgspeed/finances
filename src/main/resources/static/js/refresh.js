function beginrefresh() {
    setInterval("refresh()", 65000)
}
function refresh() {
    method = "post";

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", "/");

    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("name", "refresh");
    form.appendChild(hiddenField);

    $(document.body).append(form);

    form.submit();
}
window.onload = beginrefresh;
