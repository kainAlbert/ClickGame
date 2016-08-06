package Application;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Object.ObjectManager;

public class Application extends JFrame implements MouseListener,MouseMotionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 6296962141376967263L;

	private static Panel mPanel;
	public static ObjectManager mObj;		// オブジェクト管理者
	public static int mID;								// プレイヤーID

	public Application() {

		//IPアドレスの入力ダイアログを開く
		String ipAddress = JOptionPane.showInputDialog(null,"IPアドレスを入力してください","IPアドレスの入力",JOptionPane.QUESTION_MESSAGE);

		//入力がないときは，"localhost"とする
		if(ipAddress.equals("") ){ ipAddress = "localhost"; }

		mPanel = new Panel( this );
		this.add(mPanel);

		//ウィンドウを作成する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MyClient");
		setSize( (int)Define.WINDOW_SIZE.x, (int)Define.WINDOW_SIZE.y );

		// オブジェクト管理者生成
		mObj = new ObjectManager( this, mPanel );

		// IDの初期化
		mID = 0;

		//サーバに接続する
		Socket socket = null;
		try {
			//10000はポート番号．IP Addressで接続するPCを決めて，ポート番号でそのPC上動作するプログラムを特定する
			socket = new Socket(ipAddress, 10000);
		} catch (UnknownHostException e) {
			System.err.println("ホストの IP アドレスが判定できません: " + e);
		} catch (IOException e) {
			System.err.println("エラーが発生しました: " + e);
		}

		//受信用のスレッドを作成する
		MesgRecvThread mrt = new MesgRecvThread(socket);

		//スレッドを動かす（Runが動く）
		mrt.start();
	}

	// メイン
	public static void main(String[] args) {

		// アプリケーションを生成
		Application net = new Application();

		// 初期化
		mObj.getCM().initialize(net, mPanel);

		// 画面表示
		net.setVisible(true);

		mID = 1;

		while( true ){
			mObj.update();

			net.repaint();
		}
	}

	//ボタンをクリックしたときの処理
	public void mouseClicked(MouseEvent e) {
//		JButton theButton = (JButton)e.getComponent();//クリックしたオブジェクトを得る．型が違うのでキャストする
//		String theArrayIndex = theButton.getActionCommand();//ボタンの配列の番号を取り出す
//
//		mObj.getCM().changeForce(  Integer.parseInt(theArrayIndex) );
//
//		Icon theIcon = theButton.getIcon();//theIconには，現在のボタンに設定されたアイコンが入る
//		//System.out.println(theIcon);//デバッグ（確認用）に，クリックしたアイコンの名前を出力する
//
////				if(theIcon == whiteIcon){//アイコンがwhiteIconと同じなら
////					theButton.setIcon(blackIcon);//blackIconに設定する
////				}else{
////					theButton.setIcon(whiteIcon);//whiteIconに設定する
////				}
//		repaint();//画面のオブジェクトを描画し直す
	}

	//マウスがオブジェクトに入ったときの処理
	public void mouseEntered(MouseEvent e) {}

	//マウスがオブジェクトから出たときの処理
	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {//マウスでオブジェクトを押したときの処理（クリックとの違いに注意）
		//System.out.println("マウスを押した");
	}

	public void mouseReleased(MouseEvent e) {//マウスで押していたオブジェクトを離したときの処理
		//System.out.println("マウスを放した");

		JButton theButton = (JButton)e.getComponent();//クリックしたオブジェクトを得る．型が違うのでキャストする
		String theArrayIndex = theButton.getActionCommand();//ボタンの配列の番号を取り出す

		mObj.getCM().changeForce(  Integer.parseInt(theArrayIndex) );

		repaint();//画面のオブジェクトを描画し直す
	}

	//マウスでオブジェクトとをドラッグしているときの処理
	public void mouseDragged(MouseEvent e) {
//		System.out.println("マウスをドラッグ");
//		JButton theButton = (JButton)e.getComponent();//型が違うのでキャストする
//		String theArrayIndex = theButton.getActionCommand();//ボタンの配列の番号を取り出す
//
//		Point theMLoc = e.getPoint();//発生元コンポーネントを基準とする相対座標
//		System.out.println(theMLoc);//デバッグ（確認用）に，取得したマウスの位置をコンソールに出力する
//		Point theBtnLocation = theButton.getLocation();//クリックしたボタンを座標を取得する
//		theBtnLocation.x += theMLoc.x-15;//ボタンの真ん中当たりにマウスカーソルがくるように補正する
//		theBtnLocation.y += theMLoc.y-15;//ボタンの真ん中当たりにマウスカーソルがくるように補正する
//		theButton.setLocation(theBtnLocation);//マウスの位置にあわせてオブジェクトを移動する
//
//		//送信情報を作成する（受信時には，この送った順番にデータを取り出す．スペースがデータの区切りとなる）
//		String msg = "MOVE"+" "+theArrayIndex+" "+theBtnLocation.x+" "+theBtnLocation.y;
//
//		//サーバに情報を送る
//		MesgRecvThread.outServer(msg);
//
//		repaint();//オブジェクトの再描画を行う
	}

	//マウスがオブジェクト上で移動したときの処理
	public void mouseMoved(MouseEvent e) {}

	// セッター
	public static void setID( int id ){ mID = id; }

	// ゲッター
	public static ObjectManager getObj(){ return mObj; }
	public static int getID(){ return mID; }
}
