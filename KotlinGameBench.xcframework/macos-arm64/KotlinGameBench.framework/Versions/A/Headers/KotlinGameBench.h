#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class KGBConnectBoard, KGBConnectGameEngine, KGBConnectMove, KGBKotlinArray<T>, KGBKotlinEnum<E>, KGBKotlinEnumCompanion, KGBKotlinException, KGBKotlinIllegalStateException, KGBKotlinRuntimeException, KGBKotlinThrowable, KGBPlayer;

@protocol KGBConnectController, KGBConsole, KGBKotlinComparable, KGBKotlinIterator, KGBMove, KGBSearchableBoard;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface KGBBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface KGBBase (KGBBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface KGBMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface KGBMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorKGBKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface KGBNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end

__attribute__((swift_name("KotlinByte")))
@interface KGBByte : KGBNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface KGBUByte : KGBNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface KGBShort : KGBNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface KGBUShort : KGBNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface KGBInt : KGBNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface KGBUInt : KGBNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface KGBLong : KGBNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface KGBULong : KGBNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface KGBFloat : KGBNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface KGBDouble : KGBNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface KGBBoolean : KGBNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end


/**
 * Platform-agnostic interface for console/logging output.
 * Different platforms (JVM, iOS, JS, Native) can provide their own implementations.
 */
__attribute__((swift_name("Console")))
@protocol KGBConsole
@required

/**
 * Print without newline.
 */
- (void)printMessage:(id _Nullable)message __attribute__((swift_name("print(message:)")));

/**
 * Print without newline.
 */
- (void)printMessage_:(NSString *)message __attribute__((swift_name("print(message_:)")));

/**
 * Print a line with newline.
 */
- (void)printlnMessage:(id _Nullable)message __attribute__((swift_name("println(message:)")));

/**
 * Print a line with newline.
 */
- (void)printlnMessage_:(NSString *)message __attribute__((swift_name("println(message_:)")));

/**
 * Read a line from input (suspend function for potential async I/O).
 * Returns null if EOF or unsupported on platform.
 *
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)readLineWithCompletionHandler:(void (^)(NSString * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("readLine(completionHandler:)")));
@end


/**
 * Common implementation of Console using Kotlin's standard library.
 * Works on JVM, Native (macOS, iOS), and other platforms.
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CommonConsole")))
@interface KGBCommonConsole : KGBBase <KGBConsole>

/**
 * Common implementation of Console using Kotlin's standard library.
 * Works on JVM, Native (macOS, iOS), and other platforms.
 */
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));

/**
 * Common implementation of Console using Kotlin's standard library.
 * Works on JVM, Native (macOS, iOS), and other platforms.
 */
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)printMessage:(id _Nullable)message __attribute__((swift_name("print(message:)")));
- (void)printMessage_:(NSString *)message __attribute__((swift_name("print(message_:)")));
- (void)printlnMessage:(id _Nullable)message __attribute__((swift_name("println(message:)")));
- (void)printlnMessage_:(NSString *)message __attribute__((swift_name("println(message_:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)readLineWithCompletionHandler:(void (^)(NSString * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("readLine(completionHandler:)")));
@end


/**
 * Interface for game boards that can be used with the SearchEngine.
 * Defines the contract for minimax search algorithms.
 */
__attribute__((swift_name("SearchableBoard")))
@protocol KGBSearchableBoard
@required

/**
 * Returns the heuristic value of the current board position.
 * Positive values favor player 0, negative values favor player 1.
 * @return Heuristic evaluation of the position
 */
- (int32_t)getHeuristicValue __attribute__((swift_name("getHeuristicValue()")));

/**
 * Checks if the game has ended.
 * @return true if the game is over (win or draw)
 */
- (BOOL)isEndOfGame __attribute__((swift_name("isEndOfGame()")));

/**
 * Makes a move on the board.
 */
- (id<KGBMove> _Nullable)moveMove:(id<KGBMove>)move __attribute__((swift_name("move(move:)")));

/**
 * Returns a list of all valid moves for the given player.
 * @param player The player (0 or 1, where 0 represents the first player)
 */
- (NSArray<id<KGBMove>> *)nextMovesPlayer:(KGBPlayer *)player __attribute__((swift_name("nextMoves(player:)")));

/**
 * Undoes the last move made on the board.
 * @return The move that was undone, null if no moves to undo
 */
- (id<KGBMove> _Nullable)undoMove __attribute__((swift_name("undoMove()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConnectBoard")))
@interface KGBConnectBoard : KGBBase <KGBSearchableBoard>
- (instancetype)initWithRows:(int32_t)rows columns:(int32_t)columns __attribute__((swift_name("init(rows:columns:)"))) __attribute__((objc_designated_initializer));

/**
 * Creates a deep copy of the current ConnectBoard state.
 */
- (KGBConnectBoard *)doCopy __attribute__((swift_name("doCopy()")));

/**
 * Returns the current heuristic value of the ConnectBoard.
 * When game is over and there's a winner, returns extreme values:
 * - Player 1 wins: Int.MAX_VALUE
 * - Player 0 wins: Int.MIN_VALUE
 */
- (int32_t)getHeuristicValue __attribute__((swift_name("getHeuristicValue()")));

/**
 * Returns the number of moves made so far.
 */
- (int32_t)getMoveCount __attribute__((swift_name("getMoveCount()")));

/**
 * Returns a copy of the move history.
 */
- (NSArray<KGBConnectMove *> *)getMoveHistory __attribute__((swift_name("getMoveHistory()")));

/**
 * Gets the player at the specified position.
 */
- (KGBPlayer * _Nullable)getPlayerRow:(int32_t)row column:(int32_t)column __attribute__((swift_name("getPlayer(row:column:)")));

/**
 * Checks if the game has ended (either someone won or the ConnectBoard is full).
 */
- (BOOL)isEndOfGame __attribute__((swift_name("isEndOfGame()")));

/**
 * Returns true if the ConnectBoard is completely full.
 */
- (BOOL)isFull __attribute__((swift_name("isFull()")));

/**
 * Checks if a move is valid (the column exists and isn't full).
 */
- (BOOL)isValidMoveColumn:(int32_t)column __attribute__((swift_name("isValidMove(column:)")));

/**
 * Makes a move on the board using the Move interface.
 */
- (id<KGBMove> _Nullable)moveMove:(id<KGBMove>)move __attribute__((swift_name("move(move:)")));

/**
 * Makes a move for the given player in the specified column.
 * Returns the Move object if successful, or null if the move is invalid.
 */
- (KGBConnectMove * _Nullable)movePlayer:(KGBPlayer *)player column:(int32_t)column __attribute__((swift_name("move(player:column:)")));

/**
 * Returns a list of all valid moves for the current ConnectBoard state.
 * Each move represents dropping a piece in a valid column.
 */
- (NSArray<id<KGBMove>> *)nextMovesPlayer:(KGBPlayer *)player __attribute__((swift_name("nextMoves(player:)")));

/**
 * Clears the ConnectBoard and resets all state.
 */
- (void)reset __attribute__((swift_name("reset()")));

/**
 * Returns a string representation of the current ConnectBoard state for display.
 */
- (NSString *)description __attribute__((swift_name("description()")));

/**
 * Undoes the last move made on the ConnectBoard.
 * Returns the Move that was undone, or null if no moves to undo.
 */
- (id<KGBMove> _Nullable)undoMove __attribute__((swift_name("undoMove()")));

/**
 * Returns the winning player, or null if no winner yet.
 */
- (KGBPlayer * _Nullable)winner __attribute__((swift_name("winner()")));
@property (readonly) int32_t columns __attribute__((swift_name("columns")));
@property (readonly) int32_t rows __attribute__((swift_name("rows")));
@end


/**
 * Interface for providing moves and handling game UI updates.
 * Implementations can provide console, iOS, web, or other UI interactions.
 */
__attribute__((swift_name("ConnectController")))
@protocol KGBConnectController
@required

/**
 * Display the AI is thinking.
 */
- (void)displayAIThinkingPlayerSymbol:(NSString *)playerSymbol __attribute__((swift_name("displayAIThinking(playerSymbol:)")));

/**
 * Display that the column is full.
 */
- (void)displayColumnFullColumn:(int32_t)column __attribute__((swift_name("displayColumnFull(column:)")));

/**
 * Display the initial board state.
 */
- (void)displayInitialBoardBoard:(id<KGBSearchableBoard>)board __attribute__((swift_name("displayInitialBoard(board:)")));

/**
 * Display invalid input message.
 */
- (void)displayInvalidInputMessage:(NSString *)message __attribute__((swift_name("displayInvalidInput(message:)")));

/**
 * Display welcome message and initial state.
 */
- (void)displayWelcome __attribute__((swift_name("displayWelcome()")));

/**
 * Get the next move from the human player.
 * @param board Current board state
 * @param player Current player (should be WHITE for human)
 * @return Column number (0-6) or -1 if invalid/quit
 *
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getHumanMoveBoard:(id<KGBSearchableBoard>)board player:(KGBPlayer *)player completionHandler:(void (^)(KGBInt * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getHumanMove(board:player:completionHandler:)")));

/**
 * Notify that the game is over.
 */
- (void)notifyGameOverWinner:(KGBPlayer * _Nullable)winner __attribute__((swift_name("notifyGameOver(winner:)")));

/**
 * Notify that a move has been made and display the result.
 */
- (void)notifyMoveResultPlayerSymbol:(NSString *)playerSymbol column:(int32_t)column board:(id<KGBSearchableBoard>)board __attribute__((swift_name("notifyMoveResult(playerSymbol:column:board:)")));
@end


/**
 * Platform-agnostic Connect Four game controller.
 * Provides core game initialization and setup.
 * Platform-specific implementations handle running the game with appropriate coroutine/async patterns.
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConnectGame")))
@interface KGBConnectGame : KGBBase
- (instancetype)initWithController:(id<KGBConnectController>)controller __attribute__((swift_name("init(controller:)"))) __attribute__((objc_designated_initializer));

/**
 * Get the controller.
 */
- (id<KGBConnectController>)getController __attribute__((swift_name("getController()")));

/**
 * Get the game engine.
 */
- (KGBConnectGameEngine *)getEngine __attribute__((swift_name("getEngine()")));

/**
 * Start the game (suspend function for multiplatform compatibility).
 * Platforms call this with appropriate coroutine context.
 *
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)startWithCompletionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("start(completionHandler:)")));
@end


/**
 * Core game logic that's platform-agnostic.
 * Works with any MoveProvider implementation.
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConnectGameEngine")))
@interface KGBConnectGameEngine : KGBBase

/**
 * Core game logic that's platform-agnostic.
 * Works with any MoveProvider implementation.
 */
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));

/**
 * Core game logic that's platform-agnostic.
 * Works with any MoveProvider implementation.
 */
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));

/**
 * Runs the game with the given move provider.
 *
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)playGameConnectController:(id<KGBConnectController>)connectController completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("playGame(connectController:completionHandler:)")));
@end


/**
 * Interface representing a game move with player information.
 */
__attribute__((swift_name("Move")))
@protocol KGBMove
@required

/**
 * The player making this move (0 or 1, where 0 represents the first player).
 */
@property (readonly) KGBPlayer *player __attribute__((swift_name("player")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConnectMove")))
@interface KGBConnectMove : KGBBase <KGBMove>
- (instancetype)initWithPlayer:(KGBPlayer *)player column:(int32_t)column row:(int32_t)row __attribute__((swift_name("init(player:column:row:)"))) __attribute__((objc_designated_initializer));
@property (readonly) int32_t column __attribute__((swift_name("column")));
@property (readonly) KGBPlayer *player __attribute__((swift_name("player")));
@property (readonly) int32_t row __attribute__((swift_name("row")));
@end


/**
 * Example usage of SearchEngine for AI gameplay
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConnectPlayer")))
@interface KGBConnectPlayer : KGBBase

/**
 * Example usage of SearchEngine for AI gameplay
 */
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));

/**
 * Example usage of SearchEngine for AI gameplay
 */
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));

/**
 * Simple evaluation of how good a ConnectBoard position is for the AI.
 * @param board Board to evaluate
 * @return Heuristic value
 */
- (int32_t)evaluatePositionBoard:(id<KGBSearchableBoard>)board __attribute__((swift_name("evaluatePosition(board:)")));

/**
 * Gets the AI's best move for the given ConnectBoard state.
 * @param board Current board state
 * @param player The AI player (WHITE or BLACK)
 * @param depth Search depth (recommended: 4-8 for good performance)
 * @return Best move or null if no moves available
 */
- (KGBConnectMove * _Nullable)getBestMoveBoard:(id<KGBSearchableBoard>)board player:(KGBPlayer *)player depth:(int32_t)depth __attribute__((swift_name("getBestMove(board:player:depth:)")));
@end


/**
 * Platform-agnostic console-based implementation of ConnectController.
 * Works on all platforms (JVM, iOS, JS, Native) using the ConsolePrinter abstraction.
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConsoleController")))
@interface KGBConsoleController : KGBBase <KGBConnectController>

/**
 * Platform-agnostic console-based implementation of ConnectController.
 * Works on all platforms (JVM, iOS, JS, Native) using the ConsolePrinter abstraction.
 */
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));

/**
 * Platform-agnostic console-based implementation of ConnectController.
 * Works on all platforms (JVM, iOS, JS, Native) using the ConsolePrinter abstraction.
 */
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)displayAIThinkingPlayerSymbol:(NSString *)playerSymbol __attribute__((swift_name("displayAIThinking(playerSymbol:)")));
- (void)displayColumnFullColumn:(int32_t)column __attribute__((swift_name("displayColumnFull(column:)")));
- (void)displayInitialBoardBoard:(id<KGBSearchableBoard>)board __attribute__((swift_name("displayInitialBoard(board:)")));
- (void)displayInvalidInputMessage:(NSString *)message __attribute__((swift_name("displayInvalidInput(message:)")));
- (void)displayWelcome __attribute__((swift_name("displayWelcome()")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getHumanMoveBoard:(id<KGBSearchableBoard>)board player:(KGBPlayer *)player completionHandler:(void (^)(KGBInt * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getHumanMove(board:player:completionHandler:)")));
- (void)notifyGameOverWinner:(KGBPlayer * _Nullable)winner __attribute__((swift_name("notifyGameOver(winner:)")));
- (void)notifyMoveResultPlayerSymbol:(NSString *)playerSymbol column:(int32_t)column board:(id<KGBSearchableBoard>)board __attribute__((swift_name("notifyMoveResult(playerSymbol:column:board:)")));
@end

__attribute__((swift_name("KotlinComparable")))
@protocol KGBKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end

__attribute__((swift_name("KotlinEnum")))
@interface KGBKotlinEnum<E> : KGBBase <KGBKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) KGBKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Player")))
@interface KGBPlayer : KGBKotlinEnum<KGBPlayer *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) KGBPlayer *black __attribute__((swift_name("black")));
@property (class, readonly) KGBPlayer *white __attribute__((swift_name("white")));
+ (KGBKotlinArray<KGBPlayer *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<KGBPlayer *> *entries __attribute__((swift_name("entries")));
- (KGBPlayer *)opponent __attribute__((swift_name("opponent()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SearchEngine")))
@interface KGBSearchEngine : KGBBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));

/**
 * Finds the best move for the given player using minimax search.
 * @param board The current board state
 * @param player The player to find the best move for
 * @param depth The look-ahead depth (k)
 * @return The best move, or null if no valid moves available
 */
- (id<KGBMove> _Nullable)findBestMoveBoard:(id<KGBSearchableBoard>)board player:(KGBPlayer *)player depth:(int32_t)depth __attribute__((swift_name("findBestMove(board:player:depth:)")));

/**
 * Finds the best move with alpha-beta pruning for improved performance.
 * @param board The current board state
 * @param player The player to find the best move for
 * @param depth The look-ahead depth (k)
 * @return The best move, or null if no valid moves available
 */
- (id<KGBMove> _Nullable)findBestMoveWithPruningBoard:(id<KGBSearchableBoard>)board player:(KGBPlayer *)player depth:(int32_t)depth __attribute__((swift_name("findBestMoveWithPruning(board:player:depth:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConsoleKt")))
@interface KGBConsoleKt : KGBBase

/**
 * Global console instance available on all platforms.
 */
@property (class, readonly) id<KGBConsole> console __attribute__((swift_name("console")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MainKt")))
@interface KGBMainKt : KGBBase
+ (void)main __attribute__((swift_name("main()")));

/**
 * macOS/Native-specific wrapper for ConnectGame that provides blocking execution.
 */
+ (void)runConnectGame __attribute__((swift_name("runConnectGame()")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface KGBKotlinThrowable : KGBBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(KGBKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(KGBKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));

/**
 * @note annotations
 *   kotlin.experimental.ExperimentalNativeApi
*/
- (KGBKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) KGBKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("KotlinException")))
@interface KGBKotlinException : KGBKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(KGBKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(KGBKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinRuntimeException")))
@interface KGBKotlinRuntimeException : KGBKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(KGBKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(KGBKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIllegalStateException")))
@interface KGBKotlinIllegalStateException : KGBKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(KGBKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(KGBKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.4")
*/
__attribute__((swift_name("KotlinCancellationException")))
@interface KGBKotlinCancellationException : KGBKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(KGBKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(KGBKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface KGBKotlinEnumCompanion : KGBBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) KGBKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface KGBKotlinArray<T> : KGBBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(KGBInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<KGBKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("KotlinIterator")))
@protocol KGBKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
