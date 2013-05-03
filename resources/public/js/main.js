var $container = $('.container')
var $form = $('.allowance-form').submit(always(false))
var $successMsg = $('.success-msg')
var $errorMsg = $('.error-msg')

var countriesS = $.getAsObservable(context+'/countries')
var docReadyS = $(document).readyAsObservable()
var startDateS = $('.start-date').changeAsObservable()
var endDateS = $('.end-date').changeAsObservable()
var submitS = $form.submitAsObservable()
var countriesToPopulateS = docReadyS
  .map(always(countriesS))
  .switchLatest()
var submitResponseS = submitS
  .flatMap(postForm)
  .repeat()

countriesToPopulateS.subscribe(populateCountries)
submitResponseS.subscribe(showResponse)


// Functional helpers

function always(x) {
  return function() {
    return x
  }
}

// State mutation and I/O

function populateCountries(response) {
  $('.countries').empty().append(response.data.map(createOption))
}
function postForm(allowance) {
  return $.postAsObservable(context+'/allowance', $form.serialize()).materialize()
}
function showResponse(notification) {
  switch (notification.kind) {
    case 'N': showSuccessMsg(notification.value); break
    case 'E': showErrorMsg(); break
  }
}
function showMsg(title, body, type) {
  $('.alert').remove()
  var $msg = $('<div>').addClass('success-msg alert alert-'+type)
    .append($('<button>').addClass('close').attr('data-dismiss', 'alert').attr('type', 'button').html('&times;'))
    .append($('<strong>').text(title))
    .append(textNode(' '+body))
  $container.prepend($msg)
  setTimeout(function() { $msg.css('opacity', 1) }, 100)
}
function showSuccessMsg(response) {
  var booty = response.data / 100.0
  showMsg('Thank you!', 'Your allowance claims have been submitted. Cashing in '+booty+' € worth of mad dough :D', 'success')
}
function showErrorMsg() {
  showMsg('Oops!', 'There was an error processing your claims. No cash for you today :(', 'error')
}
function createOption(country) {
  return $('<option>').attr('value', country).text(country)
}
function textNode(str) {
  return document.createTextNode(str)
}
function log(/*args*/) {
  console.log.apply(console, arguments)
}
function logWith(label) {
  return function(/*args*/) {
    console.log.apply(console, Array.prototype.concat.apply([label], arguments))
  }
}
function logAllObserver(label) {
  return Rx.Observer.create(logWith(label + ' - Value:'), logWith(label), logWith(label + ' - Completed'))
}
