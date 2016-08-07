package Object;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.MesgRecvThread;
import Object.Character.Bullet;
import Object.Character.CharacterBase;
import Object.Character.GuardBase;

public class GetMsg {

	public static void getMsg( String msg ){

		//入力データ分類
		String[] t = msg.split( Define.STR_D );

		// コマンドが1つだけならIDとみなし、格納する
		if( Application.getID() == 0 && t.length == 1 ){

			int id = Integer.parseInt( t[0] ) % 2;

			System.out.println(id + 1);

			Application.setID( id + 1 );
			return;
		}

		// IDが自分と同じなら終了
		if( Integer.parseInt(t[0]) == Application.getID() ) return;

		// ゲームスタートの処理
		if( t[1].equals( Define.STR_GAME_START ) && !Application.getObj().getIsStart() ){

			Application.getObj().goStart();
			MesgRecvThread.outServer( Application.getID() + Define.STR_D + Define.STR_GAME_START );
			return;
		}

		// ゲームオーバーの処理
		if( t[1].equals( Define.STR_GAME_OVER ) ){

			Application.getObj().isWin();
			return;
		}

		// ボタンクリック時の処理
		if( t[1].equals( Define.STR_CHANGE_FORCE ) ){

			Application.getObj().getCM().changeForce(  Integer.parseInt( t[2] ), Integer.parseInt( t[0] ) );
			return;
		}

		// 弾を撃つ処理
		if( t[1].equals( Define.STR_CREATE_BULLET ) ){

			GSvector2 pos = Application.getObj().getCM().getBaseList().get( Integer.parseInt(t[2]) ).getPos();
			int force = Application.getID() == Define.TYPE_PLAYER ? Define.TYPE_ENEMY : Define.TYPE_PLAYER;

			CharacterBase b = new Bullet( new GSvector2( pos.x, pos.y ), force );
			b.initialize();

			if( force == Define.TYPE_PLAYER ){

				Application.getObj().getCM().addPlayerBulletList( b );
			}
			if( force == Define.TYPE_ENEMY ){

				Application.getObj().getCM().addEnemyBulletList( b );
			}

			return;
		}

		// ガードの当たり判定処理
		if( t[1].equals( Define.STR_COLLISION_GUARD ) ){

			CharacterBase g = Application.getObj().getCM().getBaseList().get( Integer.parseInt(t[2]) );

			((GuardBase)g).guard( Double.parseDouble( t[3] ) );
			return;
		}

		// ベース全移動の処理
		if( t[1].equals( Define.STR_ALL_MOVE ) ){

			Application.getObj().getCM().moveBase();
			return;
		}

		// リスト移動の処理
		if( t[1].equals( Define.STR_MOVE_LIST ) ){

			Application.getObj().getCM().getBaseList().get( Integer.parseInt(t[2]) ).setPos(
					Double.parseDouble(t[3]), Double.parseDouble(t[4]));
			return;
		}
	}
}
