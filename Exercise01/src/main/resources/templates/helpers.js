const fetchCoastersData = () => {
    return fetch('./data.json')  // ← Aquí está la ruta local
        .then(response => response.json())
        .catch(err => {
            console.error('No se pudo cargar data.json:', err)
            return []
        })
}

const getDataColors = opacity => {
    const colors = ['#7448c2', '#21c0d7', '#d99e2b', '#cd3a81', '#9c99cc', '#e14eca', '#ffffff', '#ff0000', '#d6ff00', '#0038ff']
    return colors.map(color => opacity ? `${color + opacity}` : color)
}

const getCoastersByYear = (coasters, years) => {
    return years.map(range => {
        const [from, to] = range.split('-')
        return coasters.filter(c => c.year >= from && c.year <= to).length
    })
}

const updateChartData = (chartId, data, label) => {
    const chart = Chart.getChart(chartId)
    chart.data.datasets[0].data = data
    chart.data.datasets[0].label = label
    chart.update()
}
