package com.taisheng.now.bussiness.me;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;

/**
 * Created by dragon on 2019/6/28.
 */

public class YisixieyiActivity extends BaseActivity {
    View iv_back;
    TextView tv_fuwuxieyineirong;
    String fuwuxieyineirong = "您的个人信息安全对我们来说至关重要。一直以来,宏晟健康都致力于为每位用户提供更安全的互联网环境。我们将依据《中华人民共和国网络安全法》、《信息安全技术个人信息安全规范》(GB/T35273-2017)以及其他相关法律法规和技术规范收集和使用您的个人信息,以帮助我们向您提供更优质的产品和/或服务。《宏晟健康隐私保护政策》(以下简称“本政策”)更透明地呈现我们收集和使用您个人信息的情况,以及您享有的对个人信息的控制权。我们承诺会对您的个人信息和其它数据进行严格保密,并严格按照本政策所阐述的内容处理您的个人信息。我们会根据您的同意和其它可处理您个人信息的法律依据收集、使用、存储、共享和转移您的个人信息.\n" +
            "以下是《宏晟健康隐私保护政策》的要点说明\n" +
            "1.为帮助您注册并使用宏晟健康相关产品和服务,我们可能收集与提供服务相关的个人信息,您有权拒绝或撤回授权\n" +
            "2.您可以访问、更正、删除您的个人信息,改变您授权同意的范围,注销您的帐号,我们为您提供了行使这些权利的途径\n" +
            "3.我们采取了互联网业内标准的技术措施和数据安全措施来保护您的个人信息安全。\n" +
            "4.除非再次征得您的同意,我们不会将您的个人信息用于本政策未载明的其他目的。\n" +
            "5.当您对本政策有任何疑问,可以和我们进一步联系与咨询。\n" +
            "《宏晟健康隐私保护政策》特别提示\n" +
            "请您在使用我们的各项产品和/或服务前,仔细阅读并充分理解本政策。重点内容我们已采用粗体表示,希望您特别关注。一旦您使用或继续使用宏晟健康产品/服务印表示您同意我们按照本政策处理您的相关信息。\n" +
            "如宏晟健康隐私保护政策为了便于您理解本政策中的给予详细介绍，我们可能会不时对《宏晟健康隐私保护政策》进行修改。《宏晟健康隐私保护政策》发生変更时,我们会在版本更新后以推送通知、弹商等形式向您展示变更后的内容。\n" +
            "请您注意,只有在您确认同意变更后,我们才会按照更新后的《宏晟健康隐私保护政策》收集、使用、处理和存储您的个人信息;您可以选择不同意将导致您无法使用宏晟健康的服务和功能。\n" +
            "2.宏晟健康处理个人信息的法律依据\n" +
            "如果您是中华人民共和国大陆地区的用户,我们将依据《中华人民共和国网络安全法》、《信息安全技术个人信息安全规范》(GB/T35273-2017)以及其他相关法律法规收集和使用您的个人信息,为您提供宏晟健康的产品和/或服务。我们通常只会在征得您同意的情况下收集您的个人信息。在某些情况下,我们可能还会基于法律义务或者履行合同之必需向您收集个人信息,或者可能需要个人信息来保护您的重要利益或其他人的利益。\n" +
            "3.我们如何收集和使用您的个人信息\n" +
            "我们会遵循正当、合法、必要的原则,出于本政策所述的以下目的,收集和使用您在使用服务过程中主动提供或因使用宏晟健康产品和/或服务而产生的个人信息。如果我们要将您的个人信息用于本政策未载明的其它用途,或基于特定目的将收集而来的信息用干其他目的,我们将以合理的方式向您告知,并在使用前再次征得您的同意\n" +
            "3,1帮助您成为我们的在线用户\n" +
            "1.由于医疗行业的特殊性,为保证医疗安全,您注册并登录宏晟健康帐号时,需要向我们提供以下信息:手机号码或身份证号码(用于实名认证)、患者姓名、性别、出生日期、所在城市、与患者的关系。提供上述信息并同意《宏晟健康服务协议》和本政策后,您可以使用宏晟健康的核心业务功能,包括:浏览宏晟健康平台内的内容,发布信息(包括用图文、电话、视频等方式医生提问、申请加号、评价等),如果您选择不提供为实现宏晟健康核心业务功能的必备信息或使用其中某类功能的必备信息,将导致我们无法为您提供该功能。\n" +
            "2.在您注册宏晟健康帐号时,我们会需要您提供手机号码或身份证号码进行实名认证,如您拒提供手机号码或身份证号码进行实名验证,将导致注册不成功,因此无法使用发布信息类的相关功能。但是您可以退出注册页面后进入宏晟健康浏览平台内的相关信息内容\n" +
            "3.2向您提供产品和/或服务\n" +
            "A.向您提供宏晟健康产品和/或服务的核心业务功能\n" +
            "为实现宏晟健康的核心业务功能,我们可能需要向您收集个人信息。以下将详细列出宏晟健康的核心业务功能及为实现该功能所需收集的个人信息,若您拒绝收集,则无法使用该服务浏览功能。\n" +
            "1.浏览功能。您可浏览的内容包括医生信息、问答、评论、文章。为此,我们可能会收集您使用宏晟健康APP时的设备信息,如设备型号、唯一设备标识符、操作系统、分辨率、电信运营商等软硬件信息。我们还可能收集您的浏览器类型,以此来为您提供信息展示的最优方案。此外,在您使用浏览功能的过程中,我们会自动收集您使用宏晟健康APP的详细情况,并作为有关的网络日志保存,包括但不限于您输入的搜索关键词信息和点击的链接,您浏览的內容及评论信息,访问的日期和时间、及您请求的网页记录、操作系統、软件版本号、IP\n" +
            "信息，在此过程中我们会收集您査询的浏览记录，浏览记录包括你浏览的问答，评论，文章，您可自主删除浏览记录。\n" +
            "2 .值息发布功能。‘您注册成为宏晟健康的用户后，可在宏晟健康平台上提问，您还可以提问的医生和就诊的医生作出评论、感谢，加入医生的患友会并参与讨论话题。在此过程中，我们可能会收集您的手机号码、患者姓名、性别、出生日期、所在城市、设备信息、浏览器类型、日志信息。\n" +
            "预约功能。您实名注册成为宏晟健康的用户后，可在宏晟健康平台预约咨询。在此过程中，我们可能会收集您的手机号码、患者姓名、性别、身份证号码、出生日期、所在城市、设备信息、浏览器类型、日志信息。 \n" +
            "B1．向您提供宏晟健康产品和／或服务的附加业务功能为了向您提供更优质的产品和服务，我们可能需要收集下述信息。如果您拒绝提供下述信息，不影响您正常使用 \n" +
            "3 . 2A 项所描述的宏晟健康核心业务功能，但我们无法向您提供某些特定功能和服务。 1 ．定位功能。当您开启设备定位功能并使用宏晟健康基于位置提供的相关服务时，我们会收集有关您的位置信息。\n" +
            " 2 ．搜索功能。当您使用宏晟健康提供的搜索功能时，为了给您带来更便捷的搜索服务并不断完善宏晟健康产品和/或服务,我们可能会使用浏览器网络存储机制(包括 HTML5)和应用数据缓存,收集您设备上的信息并进行本地存储。该等关键词信息通常无法单独识别您的个人身份,不属于您的个人信息,不在本政策的限制范围内。只有当您的搜索关键词信息与您的其他信息有联结并可识别您的个人身份时,则在结合使用期间,我们会将您的搜索关键词信息作为您的个人信息,与您的搜索历史记录一同按照本政策对其进行处理与保护。\n" +
            "3.客户服务。当您向宏晟健康提起投诉、申诉或进行评价时,为了方便与您联系或帮助您解決问题,我们可能需要您提供姓名、手机号码、电子邮件及其他联系方式等个人信息。如您拒绝提供上述信息,我们可能无法向您及时反馈投诉、申诉或咨询结果。\n" +
            "4.定向推送。我们会基于收集的信息,对您的偏好、习惯、位置作特征分析和用户画像,以便为您提供更适合的定制化服务,例如向您展现或推荐相关程度更高(而非普遍推送)的搜索结果、信息流或者广告/推广信息结果为此,我们需要收集的信息包括您的设备信息、浏览器型号、日志信息、浏览记录\n" +
            "5.支付功能。您可在宏晟健康购买相应的医疗服务。\n" +
            "在您使用该功能的过程中可能会需要进行支付,在支付过程中,我们可能会收集您的第三方支付帐号(例如支付宝帐号、微信支付帐号或其他形式的银行卡信息)用于付款验证。\n" +
            "3.3我们可能从第三方间接获取您的个人信息\n" +
            "A.您关注宏晟健康相应的微信公众号以及使用宏晟健康的小程序时,可选择授权宏晟健康在符合相关法律法规要求的前提下读取并获得您在微信上登记、公布、记录的公开信息(包括昵称、头像、性别)。\n" +
            "B.如您不希望宏晟健康与您的微信号绑定,您可以通「意见与建议」提交联系我们。我们会在30日回复您的解绑请求。\n" +
            "3.4敏感信息\n" +
            "在向宏晟健康提供任何属于敏感信息的个人信息前,请您清楚考虑这种提供是恰当的并且同意您的个人敏感信息可按本政策所述的目的和方式进行处理。我们会在得到您的同意后收集和使用您的敏感信息以实现与宏晟健康业务相关的功能,并允许您对这些敏感信息的收集与使用做出不同意的选择,但是拒绝使用这些信息会影响您使用相关功能。\n" +
            "3.5征得授权同意的例外\n" +
            "根据相关法律法规的规定,在以下情形中,我们可以在不征得您的授权同意的情况下收集、使用一些必要的个人信息:\n" +
            "a.与国家安全、国防安全直接相关的;\n" +
            "b.与公共安全、公共卫生、重大公共利益直接相关的;\n" +
            "c.与犯罪侦查、起诉、审判和判決执行等直接相关的;\n" +
            "d.出于维护您或其他个人的生命、财产等重大合法权益但又很难得到本人同意的;\n" +
            "e.所收集的个人信息是您自行向社会公众公开的;\n" +
            "f.从合法公开披露的信息中收集到您的个人信息,如从合法的新闻报道、政府信息公开等渠道;\n" +
            "g.根据您的要求签订和履行合同所必需的\n" +
            "h.用于维护宏晟健康的产品和/或服务的安全稳定运行所必需的,例如发现、处置产品或服务的故障;\n" +
            "法律法规规定的其他情形。\n" +
            "3.6如果您对我们收集和使用您的个人信息的法律依据有任何疑问或需要提供进一步的信息,请通过「如何联系我们」一节提供的联系方式与我们联系。\n" +
            "4.我们如何共享、转让、公开披露您的个人信息\n" +
            "4.1共享您的个人信息\n" +
            "A.我们不会与任何公司、组织和个人共享您的个人信息,但以下情况除外事先获得您的明确授权或同意:获得您的明确同意后,我们会与其他方共享您的个人信息;\n" +
            "2.在法定情形下的共享:根据适用的法律法规、法律程序、政府的强制命令或司法裁定而需共享您的个人信息\n" +
            "3在法律要求或分许的范围内,为了保护宏晟健康及其用户或社会公众的利益、财产或安全免遭损害而有必要提供您的个人信息给第三方\n" +
            "4.与我们的关联公司共享:您的个人信息可能会在我们的关联公司之间共享。我们只会共享必要的个人信息,且这种共享受本政策所声明目的的约束。关联公司如要改变个人信息的处理目的,将再次征求您的授权同意;\n" +
            "5.与授权合作伙伴共享:为了向您提供更完善、优质的产品和服务,我们的某些服务将由授权合作伙伴提供。我们可能会与合作伙伴共享您的某些个人信息,以提供更好的客户服务和用户体验。例如,宏晟健康向您寄送时,需要与合作药店以及物流服务提供商共享您的个人信息オ能安排寄送。我们仅会出于合法、正当、必要、特定、明确的目的共享您的个人信息,并且只会共享提供服务所必要的个人信息。同时,我们会与合作伙伴签署严格的保密协定,要求他们按照我们的说明、本政策以及其他任何相关的保密和安全措施来处理您的个人信息。我们的作伙伴无权将共享的个人信息用于任何其他用途如。果您拒绝我们的合作伙伴在提供服务时收集为提供服务所必须的个人信息,将可能导致您无法在宏晟健康中使用该第三方服务。\n" +
            "鉴于健康行业的特殊性,出于患教以及帮助其他患者的目的,您与医生的交流信息将在匿名化和/或去标识化处理后,默认展示给第三方阅读。如您不希望您的匿名化和/或去标识化处理过的信息展示给第三方,可向与您交流的医生申请将沟通交流內容设置为隐私,医生有权根据情况设置。设置后,您与该医生的交流内容、动态将不会向其他用户展示。通过由我们或第三方提供的功能,您可以主动公开分享、共享信息(如向其他互联网平台转发内容),在这种情况下共享信息将被广泛并即时传送。但只要您不删除被共享的信息,该信息会一直留存在公众领域;在一些情况下,即使您删除共享信息,该等信息仍可由其他用户或与我们没有关联及不受我们控制的第三方独立地存、复制或储存,或通过其他用户或该等第三方在公众领域保存。例如,当您将您在宏晟健康提出的问题转发到其他互联网平台,该平台将可能独立的展示、绶存或保存您转发的内容,其他互联网用户也可能转发、保存您的回答,而相应的页面将不受宏晟健康的控制。此外,我们亦会提供宏晟健康的其他相关功能,允许您在公众领域分享共享信息。例如公众可以通过接入博客或微信来使用宏晟健康产品中的某些社交媒体功能。您应注意,任何您分享的共享信息均可被任何第三方阅读、收集和使用。请您审慎考虑您通过宏晟健康张贴和传输的讯息内容。\n" +
            "4.2.转让除非获取您的明确同意,我们不会将您的个人信息转让给任何公司、组织或个人。\n" +
            "如果发生合并、收购或破产清算,将可能涉及到个人信息转让,此种情况下我们会要求新的持有您个人信息的公司、组织继续受本政策的约束。如果本政策中约定的个人信息的收集、处理方式发生任何改变,该公司、组织将重新向您征求授权同意\n" +
            "如果您拒绝我们的合作伙伴在提供服务时收集为提供服务所必须的个人信息,将可能导致您无法在宏晟健康中使用该第三方服务。\n" +
            "4.3,公开披露\n" +
            "除非获取您的明确同意，我们不会公开披露您的个人信基于法律、法律程序、诉讼或政府主管部门强制性要求的情况下，我们可能会向有权机关披露您的个人信息。但我们保证，在上述情况发生时，我们会要求披露请求方必须出具与之相应的有效法律文件，并对被披露的信息采取符合法律和业界标准的安全防护措施。\n" +
            "4.4,共享、转让、公开披露个人信息授权同意的例外根据相关法律法规的规定，在以下情形中，我们可以在不征得您的授权同意的情况下共享、转让、公开披露您的个人信息：\n" +
            "A.与国家安全、国防安全有关的；\n" +
            "B与公共安全、公共卫生、重大公共利益有关的；\n" +
            "C与犯罪侦查、起诉、审判和判决执行等有关的；\n" +
            "D出于维护您或其他个人的生命、财产等重大合法权益但又很难得到本人同意的；\n" +
            "F您自行向社会公众公开的个人信息；如合法的新闻报道，政府信息公开等渠道。\n" +
            "G法律法规规定的其他情形。\n" +
            "根据法律规定，共享、转让经去标识化处理的个人信息，且确保数据接收方无法复原并重新识别个人信息主体的，属于个人信息的对外共享、转让及公开披露行为，对此类数据的保存及处理将无需另行向您通知并征得您的同意。\n" +
            "5.我们如何保护您的个人信息\n" +
            "5.1技术措施与数据安全措施\n" +
            "我们努力采取各种符合业界标准的物理、电子和管理方面的安全措施来保护您的个人信息安全。我们积极建立数据分类分级制度、数据安全管理规范、数据安全开发规范来管理规范个人信息的存储和使用，确保未收集与我们提供的服务无关的个人信息。\n" +
            "我们通过与信息接触者签署保密协议、监控和审计机制来对数据进行全面安全控制。防止您的个人信息遭到未经授权的访问、公开披露、使用、修改、损坏或丟失。\n" +
            "我们已使用符合业界标准的安全防护措施保护您提供的个人信息，防止数据遭到未经授权的访问、公开披露、使用、修改，防止数据发生损坏或丟失。我们会采取一切合理可行的措施，保护您的个人信息。例如，在您的浏览器与宏晟健康之间交换数据时受551^加密保护；我们同时对本政策「引言」中出现的网站提供安全浏览方式；我们会使用加密技术确保数据的保密性；我们会使用受信赖的保护机制防止数据遭到恶意攻击；我们会部署访问控制机制，确保只有授权人员才可以访问个人信息；以及我们会举办安全和隐私保护培训课程，加强员工对于保护个人信息重要性的认识。\n" +
            "5.2安全认证\n" +
            "我们与监管机构、第三方测评机构建立了良好的协调沟通机制，及时抵御并处置各类信息安全威胁，为您的信息安全提供全方位保障。\n" +
            "5.3安全事件处置\n" +
            "我们将尽力确保您发送给我们的任何信息的安全性，但请您理解，由于技术的限制以及在互联网行业可能存在的各生个人信息安全事件，我们将按照法律法规的要求，及时向您告知：安全事件的基本情况和可能的影响、我们已采取或将要采取的处置措施、您可自主防范和降低风险的建议和对您的补救措施，并立即启动应急预案，力求将损失最小化。我们将及时将事件相关情况以电话、推送通知等方式告知您，难以逐一告知用户时，我们会采取合理、有效的方式发布公告。同时，我们还将按照监管部门要求，主动上报个人信息安全事件的处置情况，紧密配合政府机关的工作。\n" +
            "6,您管理个人信息的权利\n" +
            "我们非常重视您对个人信息的关注，并尽全力保护您对于自己个人信息访问、更正、删除以及撤回同意的权利，以使您拥有充分的能力保障您的隐私和安全。您的权利包括：\n" +
            "6.1访问和更正您的个人信息\n" +
            "A.除法律法规规定外，您有权随时访问和更正您的个人信息，具体包括：\n" +
            "1. 您可通过电脑访问XXXXX登录【我的中心】，访问或者修改您用户名绑定的手机号码和邮箱信息；；\n" +
            "2, 您可通过【设置—更改手机号码】，访问或者修改您用户名绑定的手机号码；\n" +
            "3，您可通过【设置—手机号码】，访问或者修改您用户名下患者的手机号码信息；\n" +
            "4，您可通过【我的中心—信息管理—手机号码】，访问或者修改您用户名下患者的手机号码信息；我们会在30日回复您的访问请求。\n" +
            "6.2删除您的个人信息\n" +
            "A.我们在宏晟健康APP上提供以下方式.帮助您删除您在宏晟健康上的部分相关信息。\n" +
            "1, 您可通过【我的中心—我的账号—我的病历资料】，删除您在使用服务时上传的的资料照片；\n" +
            "2, 您可通过【我的中心—收藏与订阅—我的收藏】，删除您收藏的医生；\n" +
            "3, 您可通过【我的中心—收藏与订阅—医生动态—新增与管理医生动态】，删除您之前关注过的医生；\n" +
            "1您可通过【我的中心—收藏与订阅—疾病文章—新增与管理疾病文章】，删除您之前已经订阅的文章；\n" +
            "1, 您可通过【我—我的收藏】，点击右上角的编辑按钮，删除您之前在使用宏晟健康收藏的文章、疾病、医院、科室；\n" +
            "2, 您可通过【我—我关注的医生】，点击进入每位医生的详情页，点击右上角的按钮，选择取消关注您之前在使用宏晟健康服务关注的医生；\n" +
            "3, 您可通过搜索栏下面的【清空历史搜索】，删除您在宏晟健康的历史搜索；\n" +
            "4, 您可通过【我—设置—清空缓存】，删除您在宏晟健康的浏览记录；\n" +
            "如果我们处理个人信息的行为违反法律法规；\n" +
            "如果我们收集、使用您的个人信息，却未征得您的同意；\n" +
            "3 ．如果我们处理个人信息的行为违反了与您的约定； \n" +
            "4 ．如果您注销了宏晟健康帐号； \n" +
            "5 ．如果我们终止服务及运营。\n" +
            "以上删除请求一旦被响应，我们还将同时通知从宏晟健康获得您个人信息的第三方实体, 要求其及时删除，除非法律法规另有规定，或这些实体获得您的独立授权。当您从我们的服务中删除信息后，我们可能不会立即从备份系统中删除相应的信息，但会在备份更新时删除这些信息。\n" +
            " 6 . 3 改变您授权同意的范围每个业务功能需要一些基本的个人信息才能得以完成（见本政策第 3 部分）。对于您个人信息的收集和使用，您可以随时通过以下方式给予或收回您的授权同意。 \n" +
            "1 ．您可通过 l 设置＊设置隐私密码 1 来保护您的隐私； \n" +
            "2 ．您可通过 【 设置．推送通知设置 】 来开启或关闭「通知推送」。\n" +
            "当您收回同意后，我们将不再处理相应的个人信息。\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yinsixieyi);
        initView();
    }

    void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_fuwuxieyineirong = (TextView) findViewById(R.id.tv_fuwuxieyineirong);
        tv_fuwuxieyineirong.setText(fuwuxieyineirong);


    }
}
