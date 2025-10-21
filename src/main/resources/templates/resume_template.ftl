<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>${resume.person_information.name?no_esc} – Resume</title>

        <style>
/* --- PAGE LAYOUT --- */
@page {
    size: A4;
    margin-top: 0.25in;
}

    body {
        font-family: "Helvetica", "Arial", sans-serif;
        font-size: 12pt;
        line-height: 1.5;
        color: #222;
    }

    .page {
        box-sizing: border-box;
        margin: 0 auto;
    }

    .page:not(:last-child) {
        page-break-after: always;
    }

    /* --- HEADER --- */
    header {
        display: flex;
        align-items: flex-start;
        justify-content: flex-start;
        width: 100%;
        flex-direction: column;
        margin-bottom: 1.5rem;
        text-align: center;
    }

    header h1 {
        margin: 0 auto;
        font-size: 24pt;
    }

    header p {
        margin: 0 0;
        color: #555;
    }

    /* --- SECTION --- */
    section {
        margin-top: -0.5rem;
        margin-bottom: 1.5rem;
    }

    section h2, .personal-statement h2 {
        border-bottom: 2px solid #444;
        padding-bottom: 0.1rem;
        margin-bottom: 0.8rem;
        text-transform: uppercase;
        font-size: 13pt;
        letter-spacing: 1px;
    }

    .person-details {
        font-size: 10.5pt;
    }

    /* --- SECTION ITEM --- */
    .section-item {
        margin-bottom: -1rem;
    }

    .section-item-header {
        display: flex;
        justify-content: space-between;
        align-items: baseline;
        flex-wrap: nowrap;
        gap: 0;
        margin: -0.65rem 0 0 0;
        padding: 0;
        width: 100%;
    }

    .section-item-header h3 {
        margin: 0;
        padding: 0;
        display: inline-block;
        font-size: 11pt;
        font-weight: bold;
        flex: 0;
        width: 70%;
    }

    .section-item-header span.meta {
        font-size: 10pt;
        color: #666;
        flex: 0;
        white-space: nowrap;
        display: inline-block;
        margin-left: auto;
        text-align: right;
        width: 29%;
        padding: 0;
    }

    .section-item p.subtitle, .section-item p.meta-subtitle {
        font-size: 10.5pt;
        color: #555;
    }

    .subtitle-container {
    margin: -0.5rem 0 0 0;
    display: flex;
    justify-content: space-between;
    align-items: baseline;
    flex-wrap: nowrap;
    gap: 0;
    width: 100%;
}

.subtitle-container .subtitle {
    margin: 0;
    padding: 0;
    display: inline-block;
    flex: 0;
    width: 70%;
}

.subtitle-container .meta-subtitle {
    margin: 0;
    padding: 0;
    flex: 0;
    white-space: nowrap;
    display: inline-block;
    margin-left: auto;
    text-align: right;
    width: 29%;
}

    .personal-statement p {
        margin: -0.5rem 0 1.25rem 0;
        line-height: 1.1;
        font-size: 10.5pt;
        width: 100%;
    }

    .section-item ul {
        margin: 0.025rem 0 0 0;
    }

    .section-item ul.no-bullets {
        list-style-type: none;
        padding-left: 0rem;
        margin-top: 0.625rem;
    }

    .section-item ul li {
        line-height: 1.1;
        font-size: 10.5pt;
    }

    .section-item:has(p.subtitle) ul li:first-child {
        margin-top: -0.5rem;
    }

    .section-item ul li:last-child {
        margin-bottom: 1.25rem;
    }

    .section-item p.subtitle.no-bullets {
        margin-bottom: 0.65rem;
    }
        </style>
    </head>

    <body>
        <div class="page">
            <!-- ===== HEADER ===== -->
            <header>
                <h1>${resume.person_information.name?no_esc}</h1>

                <p class="person-details">${resume.person_information.details?join(' | ')?no_esc}</p>
            </header>

            <#if resume.personal_statement?? && resume.personal_statement?has_content>
            <div class="section-item personal-statement">
                <div class="section-item-header">
                    <#if resume.personal_statement.name?? && resume.personal_statement.name?has_content>
                    <h2>${resume.personal_statement.name}</h2>
                    </#if>
                </div>

                <#if resume.personal_statement.statement?? && resume.personal_statement.statement?has_content>
                <p>${resume.personal_statement.statement?no_esc}</p>
                </#if>
            </div>
            </#if>

            <#assign globalHasAnyMetaSubtitle = false>
            <#list resume.sections as section>
                <#if !globalHasAnyMetaSubtitle>
                    <#list section.items as item>
                        <#if item.meta_subtitle?? && item.meta_subtitle?has_content>
                            <#assign globalHasAnyMetaSubtitle = true>
                            <#break> 
                        </#if>
                    </#list>
                </#if>
            </#list>

            <!-- ===== SECTIONS ===== -->
            <#list resume.sections as section>
            <section>
                <h2>${section.name?no_esc}</h2>

                <#list section.items as item>
                <div class="section-item">
                    <div class="section-item-header">
                        <#if item.title?? && item.title?has_content>
                            <h3>${item.title?no_esc}</h3>
                        </#if>

                        <#if item.meta?? && item.meta?has_content>
                        <span class="meta"<#if globalHasAnyMetaSubtitle> style="color: black;font-weight: bold;"</#if>>${item.meta?no_esc}</span>
                        </#if>
                    </div>

                    <div class="subtitle-container">
                        <#if item.subtitle?? && item.subtitle?has_content>
                            <p class="subtitle<#if !(item.bullet_points?? && item.bullet_points?has_content)> no-bullets</#if>">${item.subtitle?no_esc}</p>
                        </#if>

                        <#if item.meta_subtitle?? && item.meta_subtitle?has_content>
                            <p class="meta-subtitle">${item.meta_subtitle?no_esc}</p>
                        </#if>
                    </div>

                    <ul class="<#if !(item.show_bullets)> no-bullets</#if>">
                    <#if item.bullet_points?? && item.bullet_points?has_content>
                        <#list item.bullet_points as point>
                            <li>${point?no_esc}</li>
                        </#list>
                    </#if>
                    </ul>
                </div>
                </#list>
            </section>
            </#list>
        </div>
    </body>
</html>
