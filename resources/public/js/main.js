var countriesS = $.getAsObservable('/countries')

$(document).readyAsObservable()
  .select(always(countriesS))
  .switchLatest()
  .subscribe(populateCountries)

function populateCountries(response) {
  $('.countries').append(response.data.map(createOption))
}
function createOption(country) {
  return $('<option>').attr('value', country).text(country)
}
function always(x) {
  return function() {
    return x
  }
}
function log(x) {
  console.log(x)
}
