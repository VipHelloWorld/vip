$(function() {

    Morris.Area({
        element: 'morris-area-chart',
        data: [{
            period: '2016-10-11',
            QQ: 2666,
            all: null,
            weibo: 100
        },{
                period: '2016-10-12',
                QQ: 2666,
                all: null,
                weibo: 100
        },{
            period: '2016-10-13',
            QQ: 2778,
            all: 2294,
            weibo: 200
        },{
                period: '2016-10-14',
                QQ: 2778,
                all: 2294,
                weibo: 200
        },{
                period: '2016-10-15',
                QQ: 2778,
                all: 2294,
                weibo: 200
        },{
                period: '2016-10-16',
                QQ: 2778,
                all: 2294,
                weibo: 200}
        ],
        xkey: 'period',
        ykeys: ['weibo',"QQ","all"],
        labels: ['weibo',"QQ","总数"],
        pointSize: 2,
        hideHover: 'auto',
        resize:false
    });
});
