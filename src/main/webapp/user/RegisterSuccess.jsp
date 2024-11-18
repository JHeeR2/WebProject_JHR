<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 성공</title>
    <link rel="stylesheet" href="../css/space-theme.css">
    <script src="https://cdn.jsdelivr.net/npm/canvas-confetti@1.5.1/dist/confetti.browser.min.js"></script>
    <style>
        .container {
            text-align: center;
            padding: 40px;
            position: relative;
            z-index: 10;
        }
        h2 {
            margin-bottom: 30px;
        }
        #confetti-canvas {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            pointer-events: none;
            z-index: 1;
        }
    </style>
</head>
<body>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <canvas id="confetti-canvas"></canvas>
    <div class="container">
        <h2>회원가입을 축하합니다</h2>
        <button class="button" onclick="location.href='LoginForm.jsp'">로그인하기</button>
    </div>
    <script src="../js/stars.js"></script>
    <script>
        // Confetti effect
        function throwConfetti() {
            const count = 200;
            const defaults = {
                origin: { y: 0.7 },
                zIndex: 5
            };

            function fire(particleRatio, opts) {
                confetti(Object.assign({}, defaults, opts, {
                    particleCount: Math.floor(count * particleRatio),
                    scalar: 1.2,
                    shapes: ['star']
                }));
            }

            fire(0.25, {
                spread: 26,
                startVelocity: 55,
                colors: ['#FFD700', '#00b0ff', '#ffffff']
            });
            fire(0.2, {
                spread: 60,
                colors: ['#FFD700', '#00b0ff', '#ffffff']
            });
            fire(0.35, {
                spread: 100,
                decay: 0.91,
                scalar: 0.8,
                colors: ['#FFD700', '#00b0ff', '#ffffff']
            });
            fire(0.1, {
                spread: 120,
                startVelocity: 25,
                decay: 0.92,
                scalar: 1.2,
                colors: ['#FFD700', '#00b0ff', '#ffffff']
            });
            fire(0.1, {
                spread: 120,
                startVelocity: 45,
                colors: ['#FFD700', '#00b0ff', '#ffffff']
            });
        }

        // Trigger confetti when the page loads
        window.addEventListener('load', throwConfetti);
    </script>
</body>
</html>