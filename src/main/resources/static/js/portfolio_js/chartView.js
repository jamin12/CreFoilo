var dom = document.getElementById('dChart');

var myChart = echarts.init(dom, null, {
    renderer: 'canvas',
    useDirtyRect: false
});
var app = {};

var option = {
    // tooltip: {   // 툴팁 제거
    //     trigger: 'item'
    // },
    legend: {
        top: '5%',
        left: 'left'
    },
    series: [
        {
            name: 'Access From',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {    // 차트 부분마다 있는 라인
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
            },
            label: {        // text 미리보기 같은거 같은데 꺼놓는 편이 좋을듯
                show: false,
                position: 'center'
            },
            emphasis: {     // 부분에 호버하면 크게 보이는 글씨
                label: {
                    show: true,
                    fontSize: '20',
                    fontWeight: 'bold'
                }
            },
            labelLine: {
                show: false
            },
            data: [     // 데이터 들어가는 부분
                { value: 100, name: 'HTML/CSS' },
                { value: 80, name: 'JavaScript' },
                { value: 30, name: 'Python' },
                { value: 50, name: 'Java' },
                { value: 10, name: 'C/C++' }
            ]
        }
    ]
};


if (option && typeof option === 'object') {
    myChart.setOption(option);
}

window.addEventListener('resize', myChart.resize);

myChart.on('click', function (params) {
    alert(`${params.name}를 눌렀습니다`)
})