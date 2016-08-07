package Object.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.MesgRecvThread;
import Application.Panel;

public class BaseFactory {

	private List<BaseFactoryStructure> mList;
	private int mTimer;
	private Random mRnd;

	// コンストラクタ
	public BaseFactory( Application app, Panel p ){

		mList = new ArrayList<BaseFactoryStructure>();
		mRnd = new Random();

		mTimer= Define.FACTORY_TIME;
	}

	// 更新
	public void update(){

		if( Application.getID() != Define.TYPE_PLAYER ) return;

		if( !Application.getObj().getIsStart() ) return;

		mTimer++;

		if( mTimer < Define.FACTORY_TIME ) return;

		mTimer = 0;

		CharacterManager cm = Application.getObj().getCM();

		// 本拠地以外のベース移動
		cm.moveBase();
		MesgRecvThread.outServer( Application.getID() + Define.STR_D + Define.STR_ALL_MOVE );

		// ベース作成
		for( int i=0; i<10; i++ ){

			createList( cm );
		}

		// リスト通りに移動
		for( int i=0; i<mList.size(); i++ ){

			cm.getBaseList().get( mList.get(i).mNumber ).setPos( mList.get(i).mPos.x, mList.get(i).mPos.y  );
			MesgRecvThread.outServer(
					Application.getID() + Define.STR_D + Define.STR_MOVE_LIST + Define.STR_D +
					mList.get(i).mNumber + Define.STR_D +
					mList.get(i).mPos.x + Define.STR_D + mList.get(i).mPos.y );
		}

		mList.clear();
	}

	// リスト作成
	private void createList( CharacterManager cm ){

		BaseFactoryStructure str = new BaseFactoryStructure();

		while( true ){

			str.mNumber = mRnd.nextInt( cm.getBaseList().size() - 2 ) + 2;

			if( !isSameNumber( str.mNumber ) ) break;
		}

		str.mPos = setPos();

		mList.add( str );
	}

	// リストに同じナンバーがいるか
	private boolean isSameNumber( int number ){

		for( int i=0; i<mList.size(); i++ ){

			if( mList.get(i).mNumber == number ) return true;
		}

		return false;
	}

	// 位置を設定
	private GSvector2 setPos(){

		GSvector2 pos = new GSvector2();

		double minx, miny, maxx, maxy;
		minx = miny = maxx = maxy = 0;

		minx = Define.FACTORY_POS_MIN.x;
		miny = Define.FACTORY_POS_MIN.y;
		maxx = Define.FACTORY_POS_MAX.x - minx;
		maxy = Define.FACTORY_POS_MAX.y - miny;

		while( true ){

			pos.x = mRnd.nextInt( (int)maxx ) + minx;
			pos.y = mRnd.nextInt( (int)maxy ) + miny;

			if( !isCollision( pos ) ) break;
		}

		return pos;
	}

	// 重なっているかどうか
	private boolean isCollision( GSvector2 pos ){

		for( int i=0; i<mList.size(); i++ ){

			if( isCollisionCircle( pos, Define.BASE_SIZE, mList.get(i).mPos, Define.BASE_SIZE * 1.5 ) ) return true;
		}

		return false;
	}

	// 円と円の判定
	private boolean isCollisionCircle( GSvector2 pos1, double radius1, GSvector2 pos2, double radius2 ){

		return Math.pow( pos1.x - pos2.x, 2 ) + Math.pow( pos1.y - pos2.y, 2 ) < Math.pow( radius1 + radius2, 2 );
	}
}
