package Application;

public interface Define {

	// ウィンドウ関連
	GSvector2 WINDOW_SIZE = new GSvector2( 1200, 900 );

	// プレイヤー属性
	int TYPE_PLAYER = 1;
	int TYPE_ENEMY = 2;

	// ベース関連
	int BASE_POS_DISTANCE = 30;
	int BASE_SIZE = 80;
	int BASE_RESIZE = 128;
	enum BASE_FORCE{ NONE, PLAYER, ENEMY }
	enum BASE_TYPE{ BASE, ENERGY, ATTACK, GUARD }
	String FILE_NAME[] = { "base", "energy", "attack", "guard" };
	double ENERGY_ADD = 10 / 60;
}
