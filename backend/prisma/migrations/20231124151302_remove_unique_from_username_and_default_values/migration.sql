-- RedefineTables
PRAGMA foreign_keys=OFF;
CREATE TABLE "new_Friend" (
    "username" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "imgSrc" TEXT NOT NULL,
    "dayStreak" INTEGER NOT NULL,
    "cardsReviewed" INTEGER NOT NULL,
    "friendOfId" INTEGER NOT NULL,
    "userId" INTEGER NOT NULL,

    PRIMARY KEY ("userId", "friendOfId"),
    CONSTRAINT "Friend_friendOfId_fkey" FOREIGN KEY ("friendOfId") REFERENCES "User" ("id") ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT "Friend_userId_fkey" FOREIGN KEY ("userId") REFERENCES "User" ("id") ON DELETE RESTRICT ON UPDATE CASCADE
);
INSERT INTO "new_Friend" ("cardsReviewed", "dayStreak", "friendOfId", "imgSrc", "name", "userId", "username") SELECT "cardsReviewed", "dayStreak", "friendOfId", "imgSrc", "name", "userId", "username" FROM "Friend";
DROP TABLE "Friend";
ALTER TABLE "new_Friend" RENAME TO "Friend";
PRAGMA foreign_key_check;
PRAGMA foreign_keys=ON;
