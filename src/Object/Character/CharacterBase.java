package Object.Character;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.MesgRecvThread;
import Application.Panel;

public class CharacterBase {

	protected BufferedImage mImage;
	protected JButton mButton;
	protected GSvector2 mPos;
	protected GSvector2 mLastPos;
	protected GSvector2 mSize;
	protected GSvector2 mFirstReSize;
	protected GSvector2 mReSize;
	protected double mAngle;
	protected int mNumber;
	protected int mType;
	protected int mForce;
	protected boolean mIsDead;

	// コンストラクタ
	public CharacterBase( Application app, Panel p, String fileName, GSvector2 pos,  int number, int type ){

		// 画像読み込み
		try{
			mImage = ImageIO.read(new File("img/" + fileName + ".png"));
		}catch( IOException e ){
			e.printStackTrace();
		}

		// メンバ変数の設定
		mPos = pos;
		mLastPos = new GSvector2( pos.x, pos.y );
		mSize = new GSvector2( Define.BASE_SIZE, Define.BASE_SIZE );
		mFirstReSize = new GSvector2( Define.BASE_RESIZE, Define.BASE_RESIZE );
		mReSize = new GSvector2( Define.BASE_RESIZE, Define.BASE_RESIZE );
		mAngle = 0;
		mForce = Define.BASE_FORCE.NONE.ordinal();
		mNumber = number;
		mType = type;
		mIsDead = false;

		if( app == null ) return;

		//アイコンの生成
		mButton = new JButton( );

		// 画像の設定
		setImage();

		//ペインに貼り付ける
		p.add( mButton );

		//ボタンの大きさと位置を設定する．(x座標，y座標, xの幅,yの幅）
		mButton.setBounds( (int)pos.x, (int)pos.y, (int)mSize.x, (int)mSize.y );

		//ボタンをマウスでさわったときに反応するようにする
		mButton.addMouseListener(app);

		//ボタンをマウスで動かそうとしたときに反応するようにする
		mButton.addMouseMotionListener(app);

		//ボタンに配列の情報を付加する（ネットワークを介してオブジェクトを識別するため）
		mButton.setActionCommand( Integer.toString(number) );
	}

	// 初期化
	public void initialize(){}

	// アイコンに画像を設定
	protected void setImage(){

		// 画像の設定
		ImageIcon image =new ImageIcon(mImage.getSubimage(
				(int)(mReSize.x - mFirstReSize.x),
				(int)(mReSize.y - mFirstReSize.y),
				(int)mFirstReSize.x,
				(int)mFirstReSize.y)
				);

		Image image2 = image.getImage().getScaledInstance( Define.BASE_SIZE, Define.BASE_SIZE, 1);
		image = new ImageIcon( image2 );

		//ボタンにアイコンを設定する
		mButton.setIcon( image );
	}

	// 更新
	public void update(){

		// 本拠地
		if( mType == Define.BASE_TYPE.BASE.ordinal() ) return;

		// ゲーム開始まで
		if( !Application.getObj().getIsStart() ){

			mButton.setBounds( 0, 0, 0, 0 );
			return;
		}

		if( mPos.y < mLastPos.y ) mPos.y += Define.BASE_SPEED;

		// 現在の位置をボタンに割り当てる
		mButton.setBounds( (int)mPos.x, (int)mPos.y, (int)mSize.x, (int)mSize.y );
	}

	// FORCE(勢力)の変更
	public void changeForce( int force ){

		// 本拠地の場合はサーバーにゲーム開始を知らせる
		if( mType == Define.BASE_TYPE.BASE.ordinal() ){

			MesgRecvThread.outServer( Application.getID() + Define.STR_D + Define.STR_GAME_START );
			return;
		}

		if( !Application.getObj().getIsStart() ) return;

		mForce = force;

		mReSize.x = ( force + 1 ) * Define.BASE_RESIZE;

		// 画像を設定
		setImage();
	}

	// 終了処理
	protected void finish(){

		if( ( mPos.x > Define.WIN_LOSE_EFFECT_POS.x && mPos.x + mSize.x < Define.WIN_LOSE_EFFECT_POS.x + Define.WIN_LOSE_EFFECT_SIZE.x ) &&
				( mPos.y > Define.WIN_LOSE_EFFECT_POS.y && mPos.y + mSize.y < Define.WIN_LOSE_EFFECT_POS.y + Define.WIN_LOSE_EFFECT_SIZE.y )
				){

			mPos = new GSvector2( -1000, -1000 );
		}
	}

	// 初期位置に移動
	public void move(){

		mPos.y = -100;
		mLastPos.y = -100;

		mForce = Define.BASE_FORCE.NONE.ordinal();

		mReSize.x = ( mForce + 1 ) * Define.BASE_RESIZE;

		// 画像を設定
		setImage();
	}

	// 衝突
	public void collision(){}

	// 位置設定
	public void setPos( double x, double y ){
		mPos.x = x;
		mLastPos.y = y;
	}

	// ゲッター
	public BufferedImage getImage(){ return mImage; }
	public JButton getButton(){ return mButton; }
	public GSvector2 getPos(){ return mPos; }
	public GSvector2 getSize(){ return mSize; }
	public GSvector2 getFirstReSize(){ return mFirstReSize; }
	public GSvector2 getReSize(){ return mReSize; }
	public double getAngle(){ return mAngle; }
	public int getNumber(){ return mNumber; }
	public int getType(){ return mType; }
	public int getForce(){ return mForce; }
	public boolean getIsDead(){ return mIsDead; }
}
