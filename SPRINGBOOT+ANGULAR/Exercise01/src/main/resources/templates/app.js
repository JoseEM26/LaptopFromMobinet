Chart.defaults.color = '#fff'
Chart.defaults.borderColor = '#444'

const printCharts = () => {
    fetchCoastersData().then(coasters => {
        renderModelsChart(coasters)
        renderFeaturesChart(coasters)
        renderYearsChart(coasters)
        enableEventHandlers(coasters)
    })
}
