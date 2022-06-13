# app-publish
publish verify
    社区版本发布工具，提供社区发布件rpm包、通用包文件同步服务、数字验签服务。
    
    发布接口描述
    {host}/publish/publish POST
    {
        "gpgKeyUrl":{gpgKeyUrl},
        "keyFileName":{keyFileName},
        "rpmKey":{rpmKeyId},
        "fileKey":{fileKey},
        "tempDir":{tempDir},
        "conflict":{normal/skip/overwrite/error},
        "files":[{
            "name":{fileName},
            "size":{fileSize},
            "parentDir":{fileOriginalPath},
            "url":{fileDownloadUrl},
            "sha256":{sha256},
            "targetPath":{targetPath},
            "verifyType":{rpm/asc}
         }],
        "repoIndexList":[{
            "repoPath":{dirPath},
            "indexType":"createrepo"
        }]
    }