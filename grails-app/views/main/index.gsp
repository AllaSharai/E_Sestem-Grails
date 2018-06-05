<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="main.title.label" /></title>

    <style>
    #myCarousel {
        max-width:80%;
        margin: 0 auto
    }

    .img500px {
        width: 100%;
        max-height: 570px;
    }
    h2 {
        text-align: center;
    }
    </style>
</head>
<body>

<div class="container">
    <h2>Najnowsze wiadomości</h2>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">

            <div class="item active">
                <img class="img500px" src="https://paradisetour.pl/wp-content/uploads/2017/11/Brama-wej%C5%9Bciowa-do-Manufaktury-i-hotel-Andels-%C5%81%C3%B3d%C5%BA.jpg" alt="Los Angeles" >
                <div class="carousel-caption">
                    <h3>Manufaktura</h3>
                    <p>Mieszkania na sprzedaż</p>
                </div>
            </div>

            <div class="item">
                <img class="img500px" src="https://www.uml.lodz.pl/files/public/_processed_/2/e/csm_csm_DSC_1023-Edit_c4d5530472_cb8140fcc9.jpg" alt="Chicago" >
                <div class="carousel-caption">
                    <h3>Ulica Piotrkowska w Łodzi</h3>
                    <p>Na długi spacer</p>
                </div>
            </div>

            <div class="item">
                <img class="img500px" src="http://www.invest.lodz.pl/wgrane-pliki/big_dworzec_2.jpg" alt="New York">
                <div class="carousel-caption">
                    <h3>Łódź Fabryczna</h3>
                    <p>Na dworcu autobusowym powstał punkt informacyjny</p>
                </div>
            </div>

        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
        </a>
    </div>
</div>
</body>
</html>