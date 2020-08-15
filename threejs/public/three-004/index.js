{
    // 创建场景
    const scene = new THREE.Scene();

    // 创建模型
    const geometry1 = new THREE.BoxGeometry(100, 100, 100);
    // 创建模型
    const geometry2 = new THREE.BoxGeometry(50, 50, 50);
    // 材质对象
    const meterial1 = new THREE.MeshLambertMaterial({
        color: 0x0000FF
    });
    const meterial2 = new THREE.MeshLambertMaterial({
        color: 0xFF0000
    });
    const mesh1 = new THREE.Mesh(geometry1, meterial1);
    const mesh2 = new THREE.Mesh(geometry2, meterial2);
    mesh1.position.set(-150, 0, 0);
    mesh2.position.set(150, 0, 0);
    // 添加到场景
    scene.add(mesh1);
    scene.add(mesh2);

    // 点光源
    const point = new THREE.PointLight(0xFFFFFF);
    // 点光源位置
    point.position.set(400, 200, 300);
    // 添加到场景
    scene.add(point);

    // 环境光
    const ambient = new THREE.AmbientLight();
    scene.add(ambient);

    // 相机设置
    let width = window.innerWidth,
        height = window.innerHeight,
        k = width / height,
        // 三维场景显示范围控制系数，系数越大，显示的范围越大
        s = 200,
        camera = new THREE.OrthographicCamera(-s * k, s * k, s, -s, 1, 1000);
    // 相机位置
    camera.position.set(0, 400, 400);
    // //设置相机方向(指向的场景对象)
    camera.lookAt(scene.position);

    // 渲染器对象
    const renderer = new THREE.WebGLRenderer();
    renderer.setSize(width, height);
    // 背景色
    renderer.setClearColor(0xb9d3ff, 1);
    document.body.appendChild(renderer.domElement);

    let t0 = new Date().getTime();
    let animate = () => {
        let t1 = new Date().getTime(),
            t = t1 - t0;
        t0 = t1;
        // mesh.rotateX(0.001 * t);
        mesh1.rotateY(0.001 * t);
        mesh2.rotateZ(-0.002 * t);
        // 渲染
        renderer.render(scene, camera);
        requestAnimationFrame(animate);
    };
    animate();
}