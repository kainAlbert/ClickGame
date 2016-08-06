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
import Application.Panel;

public class CharacterBase {

	protected BufferedImage mImage;
	protected JButton mButton;
	protected GSvector2 mPos;
	protected GSvector2 mSize;
	protected GSvector2 mReSize;
	protected int mNumber;
	protected int mType;
	protected int mForce;

	// コンストラクタ
	public CharacterBase( Application app, Panel p, GSvector2 pos, int number, int type ){

		// 画像読み込み
		try{
			mImage = ImageIO.read(new File("img/" + Define.FILE_NAME[ type ] + ".png"));
		}catch( IOException e ){
			e.printStackTrace();
		}

		// メンバ変数の設定
		mPos = pos;
		mSize = new GSvector2( Define.BASE_SIZE, Define.BASE_SIZE );
		mReSize = new GSvector2( Define.BASE_RESIZE, Define.BASE_RESIZE );
		mForce = Define.BASE_FORCE.NONE.ordinal();
		mNumber = number;
		mType = type;

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

	// アイコンに画像を設定
	protected void setImage(){

		// 画像の設定
		ImageIcon image =new ImageIcon(mImage.getSubimage(
				(int)(mReSize.x - Define.BASE_RESIZE),
				(int)(mReSize.y - Define.BASE_RESIZE),
				Define.BASE_RESIZE,
				Define.BASE_RESIZE)
				);

		Image image2 = image.getImage().getScaledInstance( Define.BASE_SIZE, Define.BASE_SIZE, 1);
		image = new ImageIcon( image2 );

		//ボタンにアイコンを設定する
		mButton.setIcon( image );
	}

	// 更新
	public void update(){

		// 現在の位置をボタンに割り当てる
		mButton.setBounds( (int)mPos.x, (int)mPos.y, (int)mSize.x, (int)mSize.y );
	}

	// FORCE(勢力)の変更
	public void changeForce( int force ){

		mForce = force;

		mReSize.x = ( force + 1 ) * Define.BASE_RESIZE;

		// 画像を設定
		setImage();
	}

	// てすと
	public void test(){ mPos.x += 10; }

	// ゲッター
	public BufferedImage getImage(){ return mImage; }
	public JButton getButton(){ return mButton; }
	public GSvector2 getPos(){ return mPos; }
	public GSvector2 getSize(){ return mSize; }
	public GSvector2 getReSize(){ return mReSize; }
	public int getNumber(){ return mNumber; }
	public int getType(){ return mType; }
	public int getForce(){ return mForce; }
}
