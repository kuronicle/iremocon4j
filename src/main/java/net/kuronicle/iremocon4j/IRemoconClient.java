package net.kuronicle.iremocon4j;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import net.kuronicle.iremocon4j.dto.Timer;
import net.kuronicle.iremocon4j.exception.IRemoconException;

/**
 * 
 * @author KUROIWA, Keijiro
 *
 */
public interface IRemoconClient {

    /**
     * iRemoconに接続する。
     */
   public void connect() throws IOException;
   
   /**
    * iRemoconを切断する。
    */
   public void disconnect() throws IOException;
   
   /**
    * iRemoconにコマンドを送信する。
    * @param command iRemoconコマンド。
    * @param parameters パラメータ。
    * @return コマンド応答。
    */
   public String sendCommand(IRemoconCommand command, String... parameters) throws IRemoconException;
   
   /**
    * iRemoconとの接続を確認する。
    * @return 接続確認結果。
    */
   public boolean checkConnection();
   
   /**
    * 赤外線を発信する。
    * @param irId 発信する赤外線ID。
    * @throws IRemoconException
    */
   public void sendIr(int irId) throws IRemoconException;
   
   /**
    * 赤外線学習を開始する。
    * @param irId 学習する赤外線ID。
    * @throws IRemoconException
    */
   public void startLearningIr(int irId) throws IRemoconException;
   
   /**
    * 赤外線学習を開始する。
    * @param irid 学習する赤外線ID。
    * @param timeoutSec 学習を待つタイムアウト[秒]。0以下を指定した場合はタイムアウトしない。
    * @throws IRemoconException
    */
   public void startLearningIr(int irid, int timeoutSec) throws IRemoconException;
   
   /**
    * 赤外線学習を中止する。
    * @throws IRemoconException
    */
   public void stopLearningIr() throws IRemoconException;
   
   /**
    * タイマーをセットする。
    * @param irId 発信する赤外線ID。
    * @param timerDate 発信日時。
    * @param repeatTimeSec 繰り返し秒数。0以下を指定した場合は繰り返ししない。
    * @throws IRemoconException
    */
   public void setTimer(int irId, Date timerDate, int repeatTimeSec) throws IRemoconException;
   
   /**
    * タイマー一覧を取得する。
    * @return タイマー一覧。
    */
   public List<Timer> getTimerList();
   
   /**
    * タイマー解除。
    * @param timerId 解除するタイマーID。
    */
   public void deleteTimer(int timerId);
   
   /**
    * iRemoconの現在日時を設定する。
    * @param date 現在日時。
    */
   public void setTime(Date date);
   
   /**
    * iRemoconの現在日時を取得する。
    * @return 現在日時。
    */
   public long getTime();
   
   /**
    * iRemoconのファームバージョンを取得する。
    * @return ファームバージョン。
    */
   public String getVersion();
       
}
