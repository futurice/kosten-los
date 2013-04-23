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
  .map(postForm)
  .switchLatest()

countriesToPopulateS.subscribe(populateCountries)
submitResponseS.subscribe(showSuccessMsg, showErrorMsg)

// Functional helpers

function always(x) {
  return function() {
    return x
  }
}

// State mutation and I/O

function populateCountries(response) {
  $('.countries').append(response.data.map(createOption))
}
function postForm(allowance) {
  return $.postAsObservable(context+'/allowance', $form.serialize())
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
function log(x) {
  console.log(x)
}
function logWith(label) {
  return function(x) {
    console.log(label, x)
  }
}
