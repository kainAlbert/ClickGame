package Object.Character;

import java.awt.Container;
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

public class CharacterBase {

	private BufferedImage mImage;
	private JButton mButton;
	private GSvector2 mPos;
	private GSvector2 mSize;
	private GSvector2 mReSize;
	private int mNumber;
	private int mType;
	private int mForce;

	// コンストラクタ
	public CharacterBase( Application app, Container c, GSvector2 pos, int i ){

		// 画像読み込み
		try{
			mImage = ImageIO.read(new File("img/card.png"));
		}catch( IOException e ){
			e.printStackTrace();
		}

		// 画像の設定
		ImageIcon image = new ImageIcon( "img/card.png");
		Image image2 = image.getImage().getScaledInstance( (int)Define.BASE_SIZE.x, (int)Define.BASE_SIZE.y, 1);
		image = new ImageIcon( image2 );

		//ボタンにアイコンを設定する
		mButton = new JButton( image );

		//ペインに貼り付ける
		c.add( mButton );

		// メンバ変数の設定
		mPos = pos;
		mSize = new GSvector2( Define.BASE_SIZE.x, Define.BASE_SIZE.y );
		mReSize = new GSvector2( Define.BASE_RESIZE.x, Define.BASE_RESIZE.y );
		mForce = Define.BASE_FORCE.NONE.ordinal();
		mType = 0;

		//ボタンの大きさと位置を設定する．(x座標，y座標, xの幅,yの幅）
		mButton.setBounds( (int)pos.x, (int)pos.y, (int)mSize.x, (int)mSize.y );

		//ボタンをマウスでさわったときに反応するようにする
		mButton.addMouseListener(app);

		//ボタンをマウスで動かそうとしたときに反応するようにする
		mButton.addMouseMotionListener(app);

		//ボタンに配列の情報を付加する（ネットワークを介してオブジェクトを識別するため）
		mButton.setActionCommand( Integer.toString(i) );
	}

	// 更新
	public void update(){

		// 現在の位置をボタンに割り当てる
		mButton.setBounds( (int)mPos.x, (int)mPos.y, (int)mSize.x, (int)mSize.y );
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
}
